use bd2025;


-- QUESTÃO 01

DROP PROCEDURE maisConsoantes;

DELIMITER $$

CREATE PROCEDURE maisConsoantes(p_clicodigo01 TINYINT, p_clicodigo02 TINYINT)
BEGIN
	DECLARE v_clinome01 VARCHAR(50) DEFAULT '';
	DECLARE v_clinome02 VARCHAR(50) DEFAULT '';
	DECLARE v_clinomeconsoantes01 TINYINT DEFAULT 0;
	DECLARE v_clinomeconsoantes02 TINYINT DEFAULT 0;
	DECLARE v_cont TINYINT DEFAULT 1;

	SELECT clinome INTO v_clinome01 FROM cliente WHERE clicodigo = p_clicodigo01;
	SELECT clinome INTO v_clinome02 FROM cliente WHERE clicodigo = p_clicodigo02;

	IF v_clinome01 = v_clinome02 THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Os codigos não podem ser iguais!';
	ELSEIF v_clinome01 IS NULL THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cliente 01 não encontrado';
	ELSEIF v_clinome02 IS NULL THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cliente 02 não encontrado';
	END IF;

	WHILE v_cont <= LENGTH(v_clinome01) DO
		IF SUBSTRING(v_clinome01, v_cont, 1) NOT IN ('A','E','I','O','U','a','e','i','o','u',' ') THEN
			SET v_clinomeconsoantes01 = v_clinomeconsoantes01 + 1;
		END IF;
		SET v_cont = v_cont + 1;
	END WHILE;

	SET v_cont = 1;

	WHILE v_cont <= LENGTH(v_clinome02) DO
		IF SUBSTRING(v_clinome02, v_cont, 1) NOT IN ('A','E','I','O','U','a','e','i','o','u',' ') THEN
			SET v_clinomeconsoantes02 = v_clinomeconsoantes02 + 1;
		END IF;
		SET v_cont = v_cont + 1;
	END WHILE;

	IF v_clinomeconsoantes01 > v_clinomeconsoantes02 THEN
		SELECT c.clicodigo, c.clinome, b.bainome, v_clinomeconsoantes01 AS 'Total consoantes'
		FROM cliente c
		INNER JOIN bairro b ON b.baicodigo = c.clibaicodigo
		WHERE c.clicodigo = p_clicodigo01;

	ELSEIF v_clinomeconsoantes02 > v_clinomeconsoantes01 THEN
		SELECT c.clicodigo, c.clinome, b.bainome, v_clinomeconsoantes02 AS 'Total consoantes'
		FROM cliente c
		INNER JOIN bairro b ON b.baicodigo = c.clibaicodigo
		WHERE c.clicodigo = p_clicodigo02;

	ELSE
		SELECT c.clicodigo, c.clinome, b.bainome, v_clinomeconsoantes01 AS 'Empate'
		FROM cliente c
		INNER JOIN bairro b ON b.baicodigo = c.clibaicodigo
		WHERE c.clicodigo IN (p_clicodigo01, p_clicodigo02);
	END IF;
END $$

DELIMITER ;

call maisConsoantes(1,2);




-- QUESTÃO 02

DROP PROCEDURE inverterTexto;

DELIMITER $$

CREATE PROCEDURE inverterTexto(p_texto VARCHAR(100))
BEGIN
	DECLARE v_textoInvertido VARCHAR(100) DEFAULT '';
    DECLARE v_cont TINYINT DEFAULT length(p_texto);
    
    WHILE v_cont > 0 DO
		SET v_textoInvertido = CONCAT(v_textoInvertido, SUBSTRING(p_texto, v_cont, 1));
        SET v_cont = v_cont - 1;
	END WHILE;
    
    SELECT p_texto AS 'Texto original', v_textoInvertido AS 'Texto invertido';
END $$

DELIMITER ;

call inverterTexto('Olá mundo');
	
    

-- QUESTÃO 03

DROP PROCEDURE sp_elimina_repeticoes;

DELIMITER $$

CREATE PROCEDURE sp_elimina_repeticoes(p_texto VARCHAR(100))
BEGIN
	DECLARE v_cont TINYINT DEFAULT 1;
    DECLARE v_tam TINYINT DEFAULT LENGTH(p_texto);
    DECLARE v_char_atual VARCHAR(1) DEFAULT '';
    DECLARE v_char_proximo VARCHAR(1) DEFAULT '';
    DECLARE v_texto_sem_repeticao VARCHAR(100) DEFAULT '';
        
    WHILE v_cont <= v_tam DO
		SET v_char_atual = SUBSTRING(p_texto, v_cont, 1);
        SET v_char_proximo = SUBSTRING(p_texto, v_cont + 1, 1);
        
        IF v_char_atual != v_char_proximo THEN
			SET v_texto_sem_repeticao = CONCAT(v_texto_sem_repeticao, v_char_atual);
		END IF;
        
		SET v_cont = v_cont + 1;
	END WHILE;
    
    SELECT p_texto AS 'Texto com repetição', v_texto_sem_repeticao AS 'Texto sem repetição';
END $$

DELIMITER ;

call sp_elimina_repeticoes('BBaannco de Daadoss');









    
    