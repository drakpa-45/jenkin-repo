CommonDao.draft = SELECT Last_Application_Number AS header_id FROM t_application_sequence WHERE Type =:type
CommonDao.draftInsert=INSERT INTO t_application_sequence (Last_Application_Number, Service_Id, Type) VALUES (:seqNo,:serviceId,:type)
CommonDao.draftUpdate=UPDATE t_application_sequence SET Last_Application_Number =:SeqNo WHERE Type =:type

CommonDao.printDtls=SELECT fp.CID_Number AS cid_Number, fp.Full_Name AS name, fp.Application_Number AS application_Number,w.Action_Date AS action_Date, \
CASE WHEN fp.Cons_Type='n' THEN 'New Construction' WHEN fp.Cons_Type='r' THEN 'Renovation' WHEN fp.Cons_Type='o' \
THEN 'Other Rural Construction' END AS cons_Type,a.Net_Royalty AS net_Royalty, a.Allot_Quantity AS allot_Quantity,pm.Product_Catagory AS product_Catagory \
FROM t_fp_application fp LEFT JOIN t_fp_appl_allotment a ON fp.Application_Number=a.Application_Number LEFT JOIN t_workflow_dtls w ON w.Application_Number=fp.Application_Number \
LEFT JOIN t_fp_product_master pm ON a.FP_Product_Id = pm.FP_Product_Id WHERE fp.Application_Number=:appNo
