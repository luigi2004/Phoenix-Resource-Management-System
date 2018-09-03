
INSERT INTO ResourceType VALUES
(1,'Scrum','scrum.png','basic scrum room');
INSERT INTO ResourceType VALUES
(2,'Board','board.png','basic Board room');
INSERT INTO ResourceType VALUES
(3,'Training','training.png','basic Training room');
INSERT INTO ResourceType VALUES
(4,'Break','break.png','basic Break room');
INSERT INTO ResourceType VALUES
(5,'Recreational','rec.png','basic Recreational room');
INSERT INTO ResourceType VALUES
(6,'Confrence','confrence.png','basic Confrence room');


insert into Resources values
( '1','22', 'Scrum 1',6,'big room with TV','free','happyface.png');
insert into Resources values
( '2','177', 'Scrum 4',5,'big room with printer','booked','happyface.png');
insert into Resources values
( '3','14', 'Scrum 2',4,'big room with headphones','free','happyface.png');
insert into Resources values
( '4','12', 'Scrum 6',3,'big room with chairs','free','happyface.png');
insert into Resources values
( '5','12', 'Scrum 8',2,'big room with tables','booked','happyface.png');
insert into Resources values
( '6','22', 'Training 1',3,'big room with TV','booked','happyface.png');
insert into Resources values
( '7','177', 'Training 4',3,'big room with printer','free','happyface.png');
insert into Resources values
( '8','14', 'Training 2',3,'big room with headphones','free','happyface.png');
insert into Resources values
( '9','12', 'Training 6',3,'big room with chairs','free','happyface.png');
insert into Resources values
( '10','12', 'Training 8',3,'big room with tables','booked','happyface.png');
insert into Resources values
( '11','22', 'Break 1',4,'big room with TV','booked','happyface.png');
insert into Resources values
( '12','177', 'Break 4',4,'big room with printer','free','happyface.png');
insert into Resources values
( '13','14', 'Break 2',4,'big room with headphones','booked','happyface.png');
insert into Resources values
( '14','12', 'Break 6',4,'big room with chairs','free','happyface.png');
insert into Resources values
( '15','12', 'Break 8',4,'big room with tables','booked','happyface.png');

insert into Location values
( '1','Syntel Office', 'Phoenix','Arizona','2902 W Agua Fria Fwy','Provide two confrence rooms');
insert into Location values
( '2','Syntel Office 2', 'Collierville','Tennessee','255 Schilling Boulevard','Provide one confrence rooms');

insert into LocationResource values
('1','1','1','Description');
insert into LocationResource values
('2','2','1','Description');
insert into LocationResource values
('3','3','1','Description');
insert into LocationResource values
('4','4','2','Description');
insert into LocationResource values
('5','5','2','Description');
insert into LocationResource values
('6','6','2','Description');
insert into LocationResource values
('7','7','1','Description');
insert into LocationResource values
('8','8','2','Description');
insert into LocationResource values
('9','9','1','Description');

insert into  Feature values
('1','chair','Description','icon path');
insert into  Feature values
('2','table','Description','icon path');
insert into  Feature values
('3','tv','Description','icon path');
insert into  Feature values
('4','projector','Description','icon path');
insert into  Feature values
('5','white board','Description','icon path');
insert into  Feature values
('6','coffee','Description','icon path');
insert into  Feature values
('7','snacks','Description','icon path');
insert into  Feature values
('8','conference phone','Description','icon path');

insert into  ResourceFeature values
('1','2','1','2','description');
insert into  ResourceFeature values
('2','1','2','2','description');
insert into  ResourceFeature values
('3','2','3','2','description');
insert into  ResourceFeature values
('4','6','4','2','description');
insert into  ResourceFeature values
('5','5','5','2','description');
insert into  ResourceFeature values
('6','3','6','2','description');
insert into  ResourceFeature values
('7','3','7','2','description');
insert into  ResourceFeature values
('8','7','8','2','description');
insert into  ResourceFeature values
('9','8','9','2','description');

insert into Users values
( '1','Tim', 'tim@syntein.con','pass','type','5049929','1');
insert into Users values
( '2','Clay', 'Clay@syntein.con','pass','type','5049929','2');
insert into Users values
( '3','Mahesh', 'Mahesh@syntein.con','pass','type','5049929','2');
insert into Users values
( '4','Ahmad', 'Ahmad@syntein.con','pass','type','5049929','1');
insert into Users values
( '5','Orlando', 'Orlando@syntein.con','pass','type','5049929','1');
insert into Users values
( '6','Jasmine', 'Jasmine@syntein.con','pass','type','5049929','2');
insert into Users values
( '7','Ellen', 'Ellen@syntein.con','pass','type','5049929','2');
insert into Users values
( '8','Chris', 'Chris@syntein.con','pass','type','5049929','1');
insert into Users values
( '9','Zack', 'Xack@syntein.con','pass','type','5049929','2');
insert into Users values
( '10','Tom', '@syntein.con','pass','type','5049929','2');
insert into Users values
( '11','Mahesh', '@syntein.con','868686868','5049929','Visit','1');

insert into Booking values
('1','1','1',SYSTIMESTAMP ,SYSTIMESTAMP ,'description');
insert into Booking values
('2','2','2',SYSTIMESTAMP ,SYSTIMESTAMP  ,'description');
insert into Booking values
('3','3','3',SYSTIMESTAMP ,SYSTIMESTAMP ,'description');
insert into Booking values
('4','4','4',SYSTIMESTAMP ,SYSTIMESTAMP ,'description');
insert into Booking values
('5','5','5',SYSTIMESTAMP ,SYSTIMESTAMP  ,'description');
insert into Booking values
('6','6','6',SYSTIMESTAMP ,SYSTIMESTAMP ,'description');
insert into Booking values
('7','7','7',SYSTIMESTAMP ,SYSTIMESTAMP ,'description');
insert into Booking values
('8','8','8',SYSTIMESTAMP ,SYSTIMESTAMP  ,'description');
insert into Booking values
('9','9','9',SYSTIMESTAMP ,SYSTIMESTAMP ,'description');
insert into Booking values
('10','1','10',SYSTIMESTAMP ,SYSTIMESTAMP ,'description');
insert into Booking values
('11','2','11',SYSTIMESTAMP ,SYSTIMESTAMP  ,'description');
insert into Booking values
('12','3','1',SYSTIMESTAMP ,SYSTIMESTAMP ,'description');
insert into Booking values
('13','4','2',SYSTIMESTAMP ,SYSTIMESTAMP ,'description');
insert into Booking values
('14','5','3',SYSTIMESTAMP ,SYSTIMESTAMP  ,'description');
insert into Booking values
('15','6','4',SYSTIMESTAMP ,SYSTIMESTAMP ,'description');
insert into Booking values
('16','7','5',SYSTIMESTAMP ,SYSTIMESTAMP ,'description');
insert into Booking values
('17','8','1',SYSTIMESTAMP ,SYSTIMESTAMP  ,'description');
insert into Booking values
('18','9','6',SYSTIMESTAMP ,SYSTIMESTAMP ,'description');
insert into Booking values
('19','1','7',SYSTIMESTAMP ,SYSTIMESTAMP ,'description');
insert into Booking values
('20','2','8',SYSTIMESTAMP ,SYSTIMESTAMP  ,'description');
insert into Booking values
('21','3','9',SYSTIMESTAMP ,SYSTIMESTAMP ,'description');
insert into Booking values
('22','4','1',SYSTIMESTAMP ,SYSTIMESTAMP ,'description');
insert into Booking values
('23','5','11',SYSTIMESTAMP ,SYSTIMESTAMP  ,'description');
insert into Booking values
('24','6','10',SYSTIMESTAMP ,SYSTIMESTAMP ,'description');
insert into Booking values
('25','7','3',SYSTIMESTAMP ,SYSTIMESTAMP ,'description');
insert into Booking values
('26','7','7',SYSTIMESTAMP ,SYSTIMESTAMP  ,'description');
insert into Booking values
('27','2','9',SYSTIMESTAMP ,SYSTIMESTAMP ,'description');

insert into Visitor values
( '1','Tim', 'tim@syntein.con','9999999999','5049999','Visit','Intel');
insert into Visitor values
( '2','Clay', 'Clay@syntein.con','8888888888','2','Visit','Google');
insert into Visitor values
( '3','Mahesh', 'Mahesh@syntein.con','7777777777','3','Visit','Amex');
insert into Visitor values
( '4','Ahmad', 'Ahmad@syntein.con','6666666666','4','Visit','A');
insert into Visitor values
( '5','Orlando', 'Orlando@syntein.con','5555555555','5','Visit','B');
insert into Visitor values
( '6','Jasmine', 'Jasmine@syntein.con','4444444444','6','Visit','XYZ');
insert into Visitor values
( '7','Ellen', 'Ellen@syntein.con','3333333333','7','Visit','ZZZZ');
insert into Visitor values
( '8','Chris', 'Chris@syntein.con','2222222222','8','Visit','OW');
insert into Visitor values
( '9','Zack', 'Xack@syntein.con','1212121212','9','Visit','YWA');
insert into Visitor values
( '10','Tom', 'l@syntein.con','4747474747','10','Visit','YYY');
insert into Visitor values
( '11','Mahesh', 'a@syntein.con','8686868686','11','Visit','ZZZ');

insert into Invitation values
('1','2','1','booked','1','description');
insert into Invitation values
('2','2','3','booked','1','description');
insert into Invitation values
('3','1','2','booked','1','description');
insert into Invitation values
('4','3','4','booked','1','description');
insert into Invitation values
('5','4','5','booked','1','description');
insert into Invitation values
('6','5','6','booked','1','description');
insert into Invitation values
('7','6','7','booked','1','description');
insert into Invitation values
('8','7','8','booked','1','description');
insert into Invitation values
('9','8','9','booked','1','description');
insert into Invitation values
('10','9','10','booked','1','description');
insert into Invitation values
('11','10','11','booked','1','description');
insert into Invitation values
('12','3','12','booked','1','description');
insert into Invitation values
('13','2','13','booked','1','description');
insert into Invitation values
('14','7','14','booked','1','description');
insert into Invitation values
('15','11','15','booked','1','description');
insert into Invitation values
('16','8','16','booked','1','description');
insert into Invitation values
('17','9','17','booked','1','description');
insert into Invitation values
('18','2','18','booked','1','description');
insert into Invitation values
('19','11','19','booked','1','description');
insert into Invitation values
('20','3','20','booked','1','description');
insert into Invitation values
('21','4','21','booked','1','description');
insert into Invitation values
('22','5','23','booked','1','description');
insert into Invitation values
('23','4','22','booked','1','description');
insert into Invitation values
('24','5','24','booked','1','description');

