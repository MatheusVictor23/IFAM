use bd2025;

SET GLOBAL log_bin_trust_function_creators = 1; 

DELIMITER $$

CREATE FUNCTION f_operacoes(p_n1 INT, p_n2 INT, p_opcao CHAR(1)) RETURNS INT
BEGIN
	IF p_opcao = "+" THEN
		RETURN p_n1 + p_n2;
	ELSEIF p_opcao = "-" THEN
		RETURN p_n1 - p_n2;
	ELSEIF p_opcao = "*" THEN
		RETURN p_n1 * p_n2;
	ELSEIF p_opcao = "/" THEN
		RETURN p_n1 / p_n2;
	ELSE 
		RETURN -1;
	END IF;
END $$

DELIMITER ;

SELECT f_operacoes(5, 2, '/');









-- QUESTÃO 01: Implementar uma função meu left, com locate, substring, concat

DROP FUNCTION f_meu_left;

DELIMITER $$

CREATE FUNCTION f_meu_left(p_texto VARCHAR(255), p_posicao TINYINT) RETURNS VARCHAR(255)
BEGIN
    DECLARE v_cont TINYINT DEFAULT 1;
    DECLARE v_char VARCHAR(255) DEFAULT '';
    
    WHILE v_cont <= p_posicao DO
		SET v_char = CONCAT(v_char, SUBSTRING(p_texto, v_cont, 1));
		SET v_cont = v_cont + 1;
	END WHILE;
    
    RETURN v_char;
END $$

DELIMITER ;

SELECT f_meu_left("Olá, matheus", 3);


-- QUESTÃO 2


DROP FUNCTION f_meu_locate;

DELIMITER $$

CREATE FUNCTION f_meu_locate(p_texto01 VARCHAR(255), p_texto02 VARCHAR(255)) RETURNS VARCHAR(255)
BEGIN
	DECLARE v_cont TINYINT DEFAULT 1;
    DECLARE v_tam TINYINT DEFAULT LENGTH(p_texto02);
    DECLARE v_text VARCHAR(255) DEFAULT '';
    DECLARE v_text_tam TINYINT DEFAULT LENGTH(p_texto01);
    DECLARE v_pos VARCHAR(255) DEFAULT '';
    
    WHILE v_cont <= v_tam DO
		SET v_text = SUBSTRING(p_texto02, v_cont, v_text_tam);
        IF v_text = p_texto01 THEN
			IF v_pos <> '' THEN
				SET v_pos = CONCAT(v_pos, ',', v_cont) ;
            ELSE
				SET v_pos = CONCAT(v_cont, '', v_pos);
			END IF;
		END IF;
        SET v_cont = v_cont + 1;
	END WHILE;
    
    IF v_pos <> '' THEN
		RETURN v_pos;
    ELSE
		RETURN '0';
	END IF;
    
END $$

DELIMITER ;

SELECT f_meu_locate("de", "Banco de dados de saudavel");

