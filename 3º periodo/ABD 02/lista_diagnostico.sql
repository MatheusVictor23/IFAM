use abd_lista_diagnostico;
show tables;


/*Questão 8*/
desc funcionario;

alter table funcionario
add column fundtadmissao date,
add column funsexo char(1);

/*Questão 9*/
desc venda;

alter table venda
add column vendtcancelamento date;

/*Questão 10*/

CREATE TABLE entregador (
entcodigo INT PRIMARY KEY AUTO_INCREMENT,
entveiculo VARCHAR(20) NOT NULL,
entveiculoplaca VARCHAR(20) NOT NULL,
FOREIGN KEY(entcodigo) REFERENCES funcionario(funcodigo)
);


-- Questão 11
desc venda;

ALTER TABLE venda
ADD COLUMN entfuncodigo INT,
ADD FOREIGN KEY(entfuncodigo) references entregador(entcodigo);

ALTER TABLE venda DROP FOREIGN KEY venda_ibfk_5;  -- venda_ibfk_5 = nome da constraint
ALTER TABLE venda DROP COLUMN entfuncodigo;

ALTER TABLE venda
ADD COLUMN venentcodigo INT,
ADD CONSTRAINT fk_entcodigo FOREIGN KEY(venentcodigo) REFERENCES entregador(entcodigo);

-- Questão 12
ALTER TABLE cliente
MODIFY clinome VARCHAR(75);


-- Questão 13
DESC funcionario;

SELECT funnome, funsexo FROM funcionario;

UPDATE funcionario
set funsexo = 'M'
WHERE funcodigo = 1;

UPDATE funcionario
SET funsexo = 'M'
WHERE funcodigo = 2;

UPDATE funcionario
SET funsexo = 'M'
WHERE funcodigo = 3;


UPDATE funcionario
SET funsexo = 'F'
WHERE funcodigo = 4;

SELECT funnome, funsexo FROM funcionario;

UPDATE funcionario
SET funsexo = 'F'
WHERE funcodigo = 5;

UPDATE funcionario
SET funsexo = 'M'
WHERE funcodigo = 6;

UPDATE funcionario
SET funsexo = 'F'
WHERE funcodigo = 7;

UPDATE funcionario
SET funsexo = 'F'
WHERE funcodigo = 8;

UPDATE funcionario
SET funsexo = 'M'
WHERE funcodigo = 9;

UPDATE funcionario
SET funsexo = 'M'
WHERE funcodigo = 10;

SELECT funnome, funsexo FROM funcionario;

START TRANSACTION;
UPDATE funcionario
SET funsexo = 'M'
WHERE funcodigo = 11;

UPDATE funcionario
SET funsexo = 'F'
WHERE funcodigo = 12;

UPDATE funcionario
SET funsexo = 'F'
WHERE funcodigo = 13;

UPDATE funcionario
SET funsexo = 'F'
WHERE funcodigo = 14;

UPDATE funcionario
SET funsexo = 'F'
WHERE funcodigo = 15;

UPDATE funcionario
SET funsexo = 'F'
WHERE funcodigo = 16;

UPDATE funcionario
SET funsexo = 'F'
WHERE funcodigo = 17;

UPDATE funcionario
SET funsexo = 'F'
WHERE funcodigo = 18;

UPDATE funcionario
SET funsexo = 'F'
WHERE funcodigo = 19;

UPDATE funcionario
SET funsexo = 'M'
WHERE funcodigo = 20;

UPDATE funcionario
SET funsexo = 'M'
WHERE funcodigo = 21;

UPDATE funcionario
SET funsexo = 'M'
WHERE funcodigo = 22;

UPDATE funcionario
SET funsexo = 'M'
WHERE funcodigo = 23;

UPDATE funcionario
SET funsexo = 'F'
WHERE funcodigo = 24;

UPDATE funcionario
SET funsexo = 'F'
WHERE funcodigo = 25;

UPDATE funcionario
SET funsexo = 'M'
WHERE funcodigo = 26;

UPDATE funcionario
SET funsexo = 'M'
WHERE funcodigo = 27;

UPDATE funcionario
SET funsexo = 'F'
WHERE funcodigo = 28;

UPDATE funcionario
SET funsexo = 'F'
WHERE funcodigo = 29;

UPDATE funcionario
SET funsexo = 'M'
WHERE funcodigo = 30;

COMMIT;

-- Questão 14

SELECT clisexo,clirendamensal,clinome,
clibaicodigo,clifone,cliestcodigo,
clidtcadastro FROM cliente;

DESC cliente;
DESC bairro;
SELECT baicodigo, bainome FROM bairro;
DESC estadocivil;
SELECT estcodigo, estdescricao FROM estadocivil;

INSERT INTO cliente(
clisexo,clirendamensal,clinome,
clibaicodigo,clifone,cliestcodigo,
clidtcadastro)

VALUES('M',3000,'Matheus',6,'9299835434',1,'2025-03-07');


INSERT INTO cliente(
clisexo,clirendamensal,clinome,
clibaicodigo,clifone,cliestcodigo,
clidtcadastro)
VALUES('F', 2500, 'Maria Silva Costa',5, '9299353456',2,'2025-03-07');


-- Questão 15

DESC funcionario;
SELECT funcodigo, funnome, funcodgerente, fundtdem FROM funcionario;

UPDATE funcionario
SET fundtdem = '2025-03-07'
WHERE funcodigo = 2;


-- Questão 16

DESC produto;
SELECT procodigo, pronome, procusto, prosaldo, proativo FROM produto;

SELECT DISTINCT p.pronome 'Nome', p.procusto 'Custo', p.prosaldo 'Saldo', p.proativo 'Ativo', c.clinome 'Cliente', c.cliestcodigo 'Estado'
FROM produto p
INNER JOIN itemvenda itv ON itv.itvprocodigo = p.procodigo
INNER JOIN venda v ON v.vencodigo = itv.itvvencodigo
INNER JOIN cliente c ON c.clicodigo = v.venclicodigo
WHERE p.proativo = 1
AND p.prosaldo > 5
AND c.clisexo = 'M'
AND c.cliestcodigo = 2;


-- Questão 17

DESC cliente;
DESC funcionario;
DESC venda;
DESC vendedor;

SELECT c.clinome, c.clisexo, c.clirendamensal FROM cliente c
INNER JOIN venda ven ON ven.venclicodigo = c.clicodigo
INNER JOIN vendedor ve ON ve.vefuncodigo = ven.venfuncodigo
INNER JOIN funcionario f ON f.funcodigo = ve.vefuncodigo
WHERE clirendamensal BETWEEN 1000 AND 5000
AND clisexo = 'F'
AND fundtdem IS NOT NULL;


-- Questão 18

DESC funcionario;
DESC bairro;
DESC zona;

SELECT
f.funnome 'Nome', f.funsalario 'Salário', 
fundtdem 'Demissão', b.bainome 'Bairro', z.zonnome 'Zona' 
FROM funcionario f
INNER JOIN bairro b ON b.baicodigo = f.funbaicodigo
INNER JOIN zona z ON z.zoncodigo = b.baizoncodigo
WHERE f.fundtdem IS NOT NULL;

-- Questão 19

DESC cliente;
DESC zona;
DESC estadocivil;
DESC venda;

-- Sem group by (não conta a quantidade de compras de cada cliente)

SELECT c.clinome, z.zonnome, est.estdescricao 
FROM cliente c 
INNER JOIN bairro b ON b.baicodigo = c.clibaicodigo
INNER JOIN zona z ON z.zoncodigo = b.baizoncodigo
INNER JOIN estadocivil est ON estcodigo = c.cliestcodigo
INNER JOIN venda ven ON ven.venclicodigo = c.clicodigo;


-- Com group by(conta a quantidade de vezes que o nome, zona e estdescricao se repetem) 

SELECT
c.clinome, 
z.zonnome, 
est.estdescricao,
COUNT(ven.vencodigo) AS qtd_compras
FROM cliente c 
INNER JOIN bairro b ON b.baicodigo = c.clibaicodigo
INNER JOIN zona z ON z.zoncodigo = b.baizoncodigo
INNER JOIN estadocivil est ON estcodigo = c.cliestcodigo
INNER JOIN venda ven ON ven.venclicodigo = c.clicodigo
GROUP BY c.clinome, z.zonnome, est.estdescricao;


-- Questão 20

DESC fornecedor;
DESC cidade;
DESC produto;

-- Fornecedores que não são das cidades de Manaus, Porto velho e Rio branco
SELECT p.pronome, f.fornome, c.cidnome
FROM fornecedor f
JOIN cidade c ON f.forcidcodigo = c.cidcodigo
JOIN produto p ON p.proforcnpj = f.forcnpj
WHERE c.cidcodigo NOT IN (1, 3, 4)
ORDER BY c.cidnome;

-- Todas os fornecedores e suas cidades
SELECT p.pronome, f.fornome, c.cidnome
FROM fornecedor f
JOIN cidade c ON f.forcidcodigo = c.cidcodigo
JOIN produto p ON p.proforcnpj = f.forcnpj
ORDER BY c.cidnome;


-- Questão 21

SELECT baicodigo, bainome FROM  bairro;
SELECT zoncodigo, zonnome from zona;
SELECT estcodigo, estdescricao from estadocivil;
DESC venda;

SELECT c.clinome, b.bainome, z.zonnome, est.estdescricao
FROM cliente c
INNER JOIN bairro b ON b.baicodigo = c.clibaicodigo
INNER JOIN zona z ON z.zoncodigo = b.baizoncodigo 
INNER JOIN venda ven ON ven.venclicodigo = c.clicodigo
INNER JOIN vendedor ve ON ve.vefuncodigo = ven.venfuncodigo
INNER JOIN funcionario f ON f.funcodigo = ve.vefuncodigo
INNER JOIN estadocivil est ON est.estcodigo = f.funestcodigo
WHERE b.baicodigo IN (3,8,9)
AND z.zoncodigo = 2
AND est.estcodigo IN (1,3);



