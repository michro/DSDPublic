import random
from detectron2.utils.visualizer import Visualizer
from detectron2.data.catalog import MetadataCatalog, DatasetCatalog
import data_reg
import cv2
from detectron2.engine import DefaultTrainer
from detectron2.config import get_cfg
import os
from detectron2.engine.defaults import DefaultPredictor
from detectron2.utils.visualizer import ColorMode
import numpy as np
import sys
import warnings

warnings.filterwarnings("ignore")

scoliosis_metadata = MetadataCatalog.get("scoliosis")
cfg = get_cfg()
cfg.merge_from_file(
	"../detectron2/configs/COCO-Detection/faster_rcnn_R_50_FPN_3x.yaml"
)
cfg.MODEL.DEVICE = 'cpu'
cfg.MODEL.WEIGHTS = "output/model_final.pth"
cfg.MODEL.ROI_HEADS.SCORE_THRESH_TEST = 0.7   # set the testing threshold for this model
cfg.MODEL.ROI_HEADS.NUM_CLASSES = 1
cfg.DATASETS.TEST = ("scoliosis", )
predictor = DefaultPredictor(cfg)

#pic_to_predict = 'scoliosis_dataset/0071.jpg'
if len(sys.argv) <= 2:
    print("Usage: predict.py <ImageFileName> <OutputPath>")
    sys.exit()
pic_to_predict = sys.argv[1]
im = cv2.imread(pic_to_predict)
outputs = predictor(im)
v = Visualizer(im[:, :, ::-1],
			   metadata=scoliosis_metadata,
			   scale=1,
			   )
v = v.draw_instance_predictions(outputs["instances"].to("cpu"))

boxes = outputs["instances"].pred_boxes
ret_centers = boxes.get_centers()
x_coords = []
y_coords = []
print("Predicted centers of bone bboxes (x, y) are:")
for coord in ret_centers:
    x_coords.append(coord[0].item())
    y_coords.append(coord[1].item())
    print("%d, %d" %(coord[0], coord[1]))

# 拟合需要单值函数, 在此交换x, y坐标
y = x_coords
x = y_coords

z1 = np.polyfit(x, y, 3) # 用3次多项式拟合
p1 = np.poly1d(z1)
p2 = p1.deriv(1)
ans = []
for i in range(len(x)):
    for j in range(i, len(x)):
        if i != j:
            k1 = p2(x[i])
            k2 = p2(x[j])
            # 方向向量
            x2 = np.array([1, k1])
            y2 = np.array([1, k2])
            # 模长
            Lx = np.sqrt(x2.dot(x2))
            Ly = np.sqrt(y2.dot(y2))
            # 根据向量之间求其夹角并四舍五入
            Cobb = int((np.arccos(x2.dot(y2) / (float(Lx * Ly))) * 180 / np.pi) + 0.5)
            ans.append(Cobb)

maxcobb = max(ans)
print("Estimated Cobb angle = ", maxcobb)

szOutputFileName = sys.argv[2] + "Predicted_Cobb_%d.jpg" % (maxcobb)

img = v.get_image()[:, :, ::-1]
#cv2.imshow("Predicted", img)
#cv2.waitKey(0)
cv2.imwrite(szOutputFileName, img)
print("Output image saved.")

