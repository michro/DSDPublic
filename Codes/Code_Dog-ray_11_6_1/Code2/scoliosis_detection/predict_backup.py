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

pic_to_predict = 'scoliosis_dataset/0013.jpg'
im = cv2.imread(pic_to_predict)
outputs = predictor(im)
v = Visualizer(im[:, :, ::-1],
			   metadata=scoliosis_metadata,
			   scale=1,
			   )
v = v.draw_instance_predictions(outputs["instances"].to("cpu"))

boxes = outputs["instances"].pred_boxes
ret_centers = boxes.get_centers()
for coord in ret_centers:
    print("%d, %d" %(coord[0], coord[1]))

img = v.get_image()[:, :, ::-1]
cv2.imshow("Predicted", img)
cv2.waitKey(0)
