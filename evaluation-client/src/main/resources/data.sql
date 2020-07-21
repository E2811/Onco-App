insert into patient_evaluation (weight, intake, symptoms, ecog, patient, review) values
(55.2, 'LIQUIDS','NAUSEA','AMBULATORY', 1, '2020-07-13'),
(60, 'LIQUIDS', 'VOMIT','DISABLED', 3, '2020-05-02'),
(72.3, 'INCREASED','NAUSEA','RESTRICTED', 4, '2020-06-21'),
(68, 'LESSER', 'MOUTH_SORES','RESTRICTED',3, '2020-07-23');

insert into doctor_evaluation (metabolic,  category, eval_patient_id) values
('MINOR','A',1),
('MODERATE','B',2),
('MODERATE','A',3),
('HIGH','A',4);



