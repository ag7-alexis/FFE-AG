--AGU 25-09-2019 CREATION
--JVA-01 07-10-2019 MODIFICATION
--
--creation du proprietaire du schema ffe_ag
CREATE USER ffe_user LOGIN SUPERUSER PASSWORD 'P@ssw0rdsio';
--creation base
CREATE DATABASE ffe_agt OWNER ffe_user;
--connexion a la base 
\c ffe_ag ffe_user
--
--vider la base de données
UPDATE CLUB SET por_clu = null;
DELETE FROM PORTEUR;
