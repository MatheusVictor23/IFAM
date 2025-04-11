/* 
TRIGGERS(GATILHO)

eventos DML: INSERT, UPDATE, DELETE

tabela-timing: before, after
INSERT : NEW.CAMPO

UPDATE: NEW, OLD

DELETE: OLD

*/

DROP TRIGGER tg_atualizar_bairro_cliente;

DELIMITER $$

CREATE TRIGGER tg_atualizar_bairro_cliente AFTER UPDATE ON cliente
FOR EACH ROW
	BEGIN
		IF NEW.clibaicodigo <> OLD.clibaicodigo THEN
			UPDATE bairro SET baiqtdepessoas = baiqtdepessoas + 1
			WHERE baicodigo = NEW.clibaicodigo;
			
			UPDATE bairro SET baiqtdepessoas = baiqtdepessoas - 1
			WHERE baicodigo = OLD.clibaicodigo;
		END IF;
	END$$

DELIMITER ;



DESC bairro;

SELECT baicodigo, baiqtdepessoas FROM bairro
WHERE baicodigo = 1;

SELECT clibaicodigo, clinome from cliente where clicodigo = 1;

UPDATE cliente
SET clibaicodigo = 2
WHERE clicodigo = 1;



SELECT pronome, prosaldo from produto where procodigo = 1;


-- Atualizar o campo prosaldo após uma venda diminuindo o prosaldo conforme o itvqtde


DESC produto;

DROP TRIGGER tg_atualiza_saldo_produto;

DELIMITER $$

CREATE TRIGGER tg_atualiza_saldo_produto AFTER INSERT ON itemvenda
FOR EACH ROW
	BEGIN
		UPDATE produto
        SET prosaldo = prosaldo - new.itvqtde
        WHERE procodigo = NEW.itvprocodigo;
    END $$

DELIMITER ;


SELECT * from venda;

SELECT * FROM itemvenda;

SELECT procodigo, pronome, prosaldo from produto;

INSERT INTO itemvenda (itvvencodigo,itvprocodigo, itvqtde)
VALUES(3,5,3);