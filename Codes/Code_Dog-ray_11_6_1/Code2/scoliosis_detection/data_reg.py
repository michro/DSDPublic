from detectron2.data.datasets import register_coco_instances
register_coco_instances("scoliosis", {}, "./trainval.json", "./scoliosis_dataset")

