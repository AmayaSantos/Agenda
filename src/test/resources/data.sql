INSERT INTO perosonas
(id, dni, address, city, email, name, phone_number, surname)
VALUES(1, 1, 'one', 'one', 'one', 'personToFind', 'one', 'one');
INSERT INTO perosonas
(id, dni, address, city, email, name, phone_number, surname)
VALUES(2, 2, 'string', 'one', 'string', 'personToDelete', 'string', 'one');
INSERT INTO empresas
(id, address, city, email, name, phone_number)
VALUES(1, 'string', 'string', 'companyToFind', 'companyToFind', 'string');
INSERT INTO empresas
(id, address, city, email, name, phone_number)
VALUES(2, 'other', 'other', 'other', 'other', 'other');

INSERT INTO empresas
(id, address, city, email, name, phone_number)
VALUES(3, 'companyToAddOneContact', 'companyToAddOneContact', 'companyToAddOneContact', 'companyToAddOneContact', 'companyToAddOneContact');
INSERT INTO perosonas
(id, dni, address, city, email, name, phone_number, surname)
VALUES(3, 3, 'personToAddAsContact', 'personToAddAsContact', 'personToAddAsContact', 'personToAddAsContact', 'personToAddAsContact', 'personToAddAsContact');

INSERT INTO empresas
(id, address, city, email, name, phone_number)
VALUES(4,'companyToAddAndRemoveOneContact', 'companyToAddAndRemoveOneContact', 'companyToAddAndRemoveOneContact', 'companyToAddAndRemoveOneContact', 'companyToAddAndRemoveOneContact');
INSERT INTO perosonas
(id, dni, address, city, email, name, phone_number, surname)
VALUES(4, 3, 'personToAddAndRemoveAsContact', 'personToAddAndRemoveAsContact', 'personToAddAndRemoveAsContact', 'personToAddAndRemoveAsContact', 'personToAddAsContact', 'personToAddAndRemoveAsContact');

INSERT INTO empresas
(id, address, city, email, name, phone_number)
VALUES(5, 'companyToAddAndRemoveTwoContacts', 'companyToAddAndRemoveTwoContacts', 'companyToAddAndRemoveTwoContacts', 'companyToAddAndRemoveTwoContacts', 'companyToAddAndRemoveTwoContacts');
INSERT INTO perosonas
(id, dni, address, city, email, name, phone_number, surname)
VALUES(5, 4, 'person1ToAddAsContact', 'person1ToAddAsContact', 'person1ToAddAsContact', 'person1ToAddAsContact', 'person1ToAddAsContact', 'personToAddAsContact');
INSERT INTO perosonas
(id, dni, address, city, email, name, phone_number, surname)
VALUES(6, 4, 'person2ToAddAndRemoveAsContact', 'person2ToAddAndRemoveAsContact', 'person2ToAddAndRemoveAsContact', 'person2ToAddAndRemoveAsContact', 'person2ToAddAndRemoveAsContact', 'person2ToAddAndRemoveAsContact');

