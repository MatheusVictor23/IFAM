SHOW DATABASES;

use bd2025;
SET GLOBAL log_bin_trust_function_creators = 1; 



-- QUESTÃO 03

DROP FUNCTION f_letras;

DELIMITER $$

CREATE FUNCTION f_letras (p_texto VARCHAR(255), p_opcao VARCHAR(1))
RETURNS VARCHAR(255)
BEGIN
	DECLARE v_tam TINYINT DEFAULT LENGTH(p_texto);
    DECLARE v_cont TINYINT DEFAULT 1;
    DECLARE v_texto VARCHAR(255) DEFAULT '';
    DECLARE v_letras VARCHAR(255) DEFAULT '';

	WHILE v_cont <= v_tam DO
		SET v_texto = SUBSTRING(p_texto, v_cont, 1);
        
        IF p_opcao = "C" THEN
			IF v_texto NOT IN("A","E","I","O","U", " ") THEN
				IF v_letras = '' THEN
					SET v_letras = v_texto;
				ELSE
					SET v_letras = CONCAT(v_letras, '-', UPPER(v_texto));
				END IF;
			END IF;
		ELSEIF p_opcao = "V" THEN
			IF v_texto IN("A","E","I","O","U") THEN
				IF v_letras = '' THEN
					SET v_letras = v_texto;
				ELSE
					SET v_letras = CONCAT(v_letras, '-', UPPER(v_texto));
				END IF;
			END IF;
		END IF;
		
        SET v_cont = v_cont + 1;
	END WHILE;

	RETURN v_letras;
END $$

DELIMITER ;

SELECT f_letras ("BANCO DE DADOS", "C");









-- QUESTÃO 04

DROP FUNCTION f_meu_right;

DELIMITER $$

CREATE FUNCTION f_meu_right (p_texto VARCHAR(255), p_qtde TINYINT)
RETURNS VARCHAR(255)
BEGIN
	DECLARE v_texto VARCHAR(255) DEFAULT '';
    
    SET v_texto = SUBSTRING(p_texto, LENGTH(p_texto) - p_qtde + 1, p_qtde);
    
    RETURN v_texto;
END $$

DELIMITER ;

SELECT f_meu_right("SQL", 2) AS meu_right;








-- QUESTÃO 05

DROP FUNCTION f_meu_locate;

DELIMITER $$

CREATE FUNCTION f_meu_locate (p_locate VARCHAR(255), p_texto VARCHAR(255))
RETURNS VARCHAR(255)
BEGIN
	DECLARE v_tam TINYINT UNSIGNED DEFAULT LENGTH(p_texto);
    DECLARE v_cont TINYINT UNSIGNED DEFAULT 1;
    DECLARE v_texto VARCHAR(255) DEFAULT '';
    DECLARE v_texto_locate VARCHAR(255) DEFAULT '';
    
    WHILE v_cont <= v_tam DO
		SET v_texto = SUBSTRING(p_texto, v_cont, LENGTH(p_locate));
        
        IF v_texto = p_locate THEN
			IF v_texto_locate = '' THEN
				SET v_texto_locate = CONCAT(v_cont, '');
			ELSE
				SET v_texto_locate = CONCAT(v_texto_locate, ', ', v_cont);
			END IF;
		END IF;
        
        SET v_cont = v_cont + 1;
        
	END WHILE;
            
    RETURN v_texto_locate;
END $$

DELIMITER ;

SELECT f_meu_locate("AA", "AABBAABBAACCAA");










-- QUESTÃO 06

DROP FUNCTION f_ocorrencias;

DELIMITER $$

CREATE FUNCTION f_ocorrencias (p_char VARCHAR(255), p_texto VARCHAR(255))
RETURNS TINYINT
BEGIN
	DECLARE v_tam TINYINT UNSIGNED DEFAULT LENGTH(p_texto);
    DECLARE v_cont TINYINT UNSIGNED DEFAULT 1;
    DECLARE v_ocorrencias TINYINT UNSIGNED DEFAULT 0;
    DECLARE v_texto VARCHAR(255) DEFAULT '';
    
    WHILE v_cont <= v_tam DO
		SET v_texto = SUBSTRING(p_texto, v_cont, 1);
        
        IF v_texto = p_char THEN
			SET v_ocorrencias = v_ocorrencias + 1;
		END IF;
		
        SET v_cont = v_cont + 1;
	END WHILE;
    
    RETURN v_ocorrencias;
END $$

DELIMITER ;

SELECT f_ocorrencias("e", "paralelepípedoee");









-- QUESTÃ0 07

DROP FUNCTION f_limpa_caractere;

DELIMITER $$

CREATE FUNCTION f_limpa_caractere (p_texto VARCHAR(255))
RETURNS VARCHAR(255)
BEGIN
	DECLARE v_tam TINYINT UNSIGNED DEFAULT LENGTH(p_texto);
    DECLARE v_cont TINYINT UNSIGNED DEFAULT 1;
    DECLARE v_ocorrencias VARCHAR(255) DEFAULT '';
    DECLARE v_texto VARCHAR(255) DEFAULT '';
    
	WHILE v_cont <= v_tam DO
		SET v_texto = SUBSTRING(p_texto, v_cont, 1);
        
        IF v_texto NOT IN ("(", ")", "/", "$", "+", "%", "-", "@", "_", "!", "*") THEN
			SET v_ocorrencias = CONCAT(v_ocorrencias, v_texto);
		END IF;
        
        SET v_cont = v_cont + 1;
	END WHILE;
    
    RETURN v_ocorrencias;
END $$

DELIMITER ;

SELECT f_limpa_caractere("Pro%gra!mação e_m ban/co-d$e@da)do(s]="); -- ERRO COM A BARRA \ E CARACTERES QUE NÃO ESTÃO NO ENUNCIADO ( ]=)










-- QUESTÃO 08

DROP FUNCTION f_capital;

DELIMITER $$

CREATE FUNCTION f_capital (p_texto VARCHAR(255))
RETURNS VARCHAR(255)
BEGIN
	DECLARE v_tam TINYINT UNSIGNED DEFAULT LENGTH(p_texto);
    DECLARE v_cont TINYINT UNSIGNED DEFAULT 1;
    DECLARE v_ocorrencias VARCHAR(255) DEFAULT '';
    DECLARE v_texto VARCHAR(1) DEFAULT '';
	DECLARE v_texto_maiusculo VARCHAR(1) DEFAULT '';

    WHILE v_cont <= v_tam DO
		SET v_texto = SUBSTRING(p_texto, v_cont, 1);
        SET v_texto_maiusculo = UPPER(v_texto);
        
        IF v_cont = 1 AND v_texto <> v_texto_maiusculo THEN
			SET v_ocorrencias = CONCAT(v_ocorrencias, v_texto_maiusculo);
            
		ELSEIF SUBSTRING(p_texto, v_cont - 1, 1) = " " THEN
			SET v_ocorrencias = CONCAT(v_ocorrencias, v_texto_maiusculo);
            
 		ELSEIF v_cont > 1 AND v_texto = v_texto_maiusculo THEN
			SET v_ocorrencias = CONCAT(v_ocorrencias, LOWER(v_texto));
            
		ELSE
			SET v_ocorrencias = CONCAT(v_ocorrencias, v_texto);
		END IF;
        
        SET v_cont = v_cont + 1;
	END WHILE;
    
    RETURN v_ocorrencias;
END $$

DELIMITER ;

SELECT f_capital("BaNco de daDos");







