--
-- JBoss, Home of Professional Open Source
-- Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data into the database using SQL statements
--insert into Member (id, name, email, phone_number) values (1, 'John Smith', 'john.smith@mailinator.com', '2125551212');


--INSERT INTO product (id, title, description, prize, imagem_name) VALUES (1, 'Notebook LG N450', '3ª geração da família Intel Core.', 2200.99, 'computador.jpg');
--INSERT INTO product (id, title, description, prize, imagem_name) VALUES (2, 'Cafeteira Expresso Dolce', 'Café expresso feito', 249.00, 'cafeteira.jpg');
--INSERT INTO product (id, title, description, prize, imagem_name) VALUES (3, 'Bicicleta Caloi Aluminun Aro 26 21 Marchas', 'Pedalar proporciona diversos <b>benefícios</b>', 409.90, 'bicicleta.jpg');
--INSERT INTO product (id, title, description, prize, imagem_name) VALUES (4, 'Lava e Seca LG 6 Motion', 'A lavadora 6', 1999.00, 'lavadora_secadora.jpg');


/*
CREATE TABLE `hermeshop`.`client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));
  
  CREATE TABLE `hermeshop`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(60) NOT NULL,
  `description` TEXT NULL,
  `prize` DOUBLE NOT NULL,
  `imagem_name` VARCHAR(60) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `hermeshop`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `order_date` DATETIME NOT NULL,
  `id_client` INT NOT NULL,
  `total` DOUBLE NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `hermeshop`.`item_order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_product` INT NOT NULL,
  `unit_prize` DOUBLE NOT NULL,
  `quantity` INT NOT NULL,
  `totalPrize` DOUBLE NOT NULL,
  `id_order` INT NOT NULL,
  PRIMARY KEY (`id`));

*/