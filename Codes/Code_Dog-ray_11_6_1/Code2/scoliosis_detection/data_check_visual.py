import random
from detectron2.utils.visualizer import Visualizer
from detectron2.data.catalog import MetadataCatalog, DatasetCatalog
import data_reg
import cv2

scoliosis_metadata = MetadataCatalog.get("scoliosis")
print(scoliosis_metadata)
dataset_dicts = DatasetCatalog.get("scoliosis")

for d in random.sample(dataset_dicts, 3):
    img = cv2.imread(d["file_name"])
    visualizer = Visualizer(img[:, :, ::-1], metadata=scoliosis_metadata, scale=1)
    vis = visualizer.draw_dataset_dict(d)
    img = vis.get_image()[:, :, ::-1]
    cv2.imshow('A random labeled picture', img)
    cv2.waitKey(0)
