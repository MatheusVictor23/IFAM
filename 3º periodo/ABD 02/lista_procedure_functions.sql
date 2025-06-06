SHOW DATABASES;

use bd2025;
SET GLOBAL log_bin_trust_function_creators = 1; 







-- QUESTÃO 01

DROP FUNCTION f_incrementa_funcodigo;

DELIMITER $$

CREATE FUNCTION f_incrementa_funcodigo ()
RETURNS INT
BEGIN
	DECLARE v_incrementado INT DEFAULT 0;
    DECLARE v_max_funcodigo INT DEFAULT 0;
    
    SELECT MAX(funcodigo) FROM funcionario INTO v_max_funcodigo;
    
    SET v_max_funcodigo = v_max_funcodigo + 1;
    
    RETURN v_max_funcodigo;
END $$

DELIMITER ;




DROP FUNCTION f_valida_baicodigo;

DELIMITER $$

CREATE FUNCTION f_valida_baicodigo (p_baicodigo INT)
RETURNS BOOLEAN
BEGIN
	DECLARE v_min_baicodigo TINYINT UNSIGNED DEFAULT 0;
    DECLARE v_max_baicodigo TINYINT UNSIGNED DEFAULT 0;
	DECLARE v_valida BOOLEAN DEFAULT false;
    
    SELECT MIN(baicodigo) INTO v_min_baicodigo FROM bairro;
    SELECT MAX(baicodigo) INTO v_max_baicodigo FROM bairro;
    
    IF p_baicodigo BETWEEN v_min_baicodigo AND v_max_baicodigo THEN
		SET v_valida = TRUE;
	ELSE
		SET v_valida = FALSE;
	END IF;
    
    RETURN v_valida;
END $$

DELIMITER ;

SELECT f_valida_baicodigo(17);





DROP FUNCTION f_valida_estadocivil;

DELIMITER $$

CREATE FUNCTION f_valida_estadocivil (p_estcodigo INT)
RETURNS BOOLEAN
BEGIN
	DECLARE v_min_estcodigo TINYINT UNSIGNED DEFAULT 0;
    DECLARE v_max_estcodigo TINYINT UNSIGNED DEFAULT 0;
	DECLARE v_valida BOOLEAN DEFAULT false;
    
    SELECT MIN(estcodigo) INTO v_min_estcodigo FROM estadocivil;
    SELECT MAX(estcodigo) INTO v_max_estcodigo FROM estadocivil;
    
    IF p_estcodigo BETWEEN v_min_estcodigo AND v_max_estcodigo THEN
		SET v_valida = TRUE;
	ELSE
		SET v_valida = FALSE;
	END IF;
    
    RETURN v_valida;
END $$

DELIMITER ;

SELECT f_valida_estadocivil(4);



DROP PROCEDURE sp_valida_funcionario;

DELIMITER $$

CREATE PROCEDURE sp_valida_funcionario (p_funnome VARCHAR(50), p_funsalario DOUBLE(6,2), p_funbaicodigo INT, p_funestcodigo INT)
BEGIN
	DECLARE v_valida_funbaicodigo BOOLEAN DEFAULT 0;
    DECLARE v_valida_estcodigo BOOLEAN DEFAULT 0;
    DECLARE v_funcodigo INT DEFAULT 0;
    
    SELECT f_valida_baicodigo (p_funbaicodigo) INTO v_valida_funbaicodigo;
    
    IF v_valida_funbaicodigo = 0 THEN
		SELECT "Bairro inexistente" AS Erro;
	END IF;
    
    SELECT f_valida_estadocivil (p_funestcodigo) INTO v_valida_estcodigo;
    
    IF v_valida_estcodigo = 0 THEN
		SELECT "Estado civil inexistente" AS Erro;
	END IF;
    
	SELECT f_incrementa_funcodigo() INTO v_funcodigo;
    
    INSERT INTO funcionario (funcodigo,funnome, funsalario, funbaicodigo, funestcodigo)
    VALUES (v_funcodigo, p_funnome, p_funsalario, p_funbaicodigo, p_funestcodigo);
    
    SELECT "Funcionario cadastrado!" AS Message, funcodigo, funnome, funsalario, funbaicodigo, funestcodigo from funcionario
    WHERE funcodigo = v_funcodigo;
END $$

DELIMITER ;

CALL sp_valida_funcionario("Matheus costa", 1500, 2, 3);

SELECT funcodigo, funnome, funsalario, funbaicodigo, funestcodigo from funcionario;

DESCRIBE funcionario;













-- QUESTÃO 02


DROP FUNCTION f_incrementa_vencodigo;

DELIMITER $$

CREATE FUNCTION f_incrementa_vencodigo ()
RETURNS INT
BEGIN
	DECLARE v_incrementado INT DEFAULT 0;
    DECLARE v_max_vencodigo INT DEFAULT 0;
    
    SELECT MAX(vencodigo) FROM venda INTO v_max_vencodigo;
    
    SET v_max_vencodigo = v_max_vencodigo + 1;
    
    RETURN v_max_vencodigo;
END $$

DELIMITER ;



DROP FUNCTION f_valida_filcodigo;

DELIMITER $$

CREATE FUNCTION f_valida_filcodigo (p_filcodigo INT)
RETURNS BOOLEAN
BEGIN
	DECLARE v_min_filcodigo INT UNSIGNED DEFAULT 0;
    DECLARE v_max_filcodigo INT UNSIGNED DEFAULT 0;
	DECLARE v_valida BOOLEAN DEFAULT false;
    
    SELECT MIN(filcodigo) INTO v_min_filcodigo FROM filial;
    SELECT MAX(filcodigo) INTO v_max_filcodigo FROM filial;
    
    IF p_filcodigo BETWEEN v_min_filcodigo AND v_max_filcodigo THEN
		SET v_valida = TRUE;
	ELSE
		SET v_valida = FALSE;
	END IF;
    
    RETURN v_valida;
END $$

DELIMITER ;


DROP FUNCTION f_valida_clicodigo;

DELIMITER $$

CREATE FUNCTION f_valida_clicodigo (p_clicodigo INT)
RETURNS BOOLEAN
BEGIN
	DECLARE v_min_clicodigo INT UNSIGNED DEFAULT 0;
    DECLARE v_max_clicodigo INT UNSIGNED DEFAULT 0;
	DECLARE v_valida BOOLEAN DEFAULT false;
    
    SELECT MIN(clicodigo) INTO v_min_clicodigo FROM cliente;
    SELECT MAX(clicodigo) INTO v_max_clicodigo FROM cliente;
    
    IF p_clicodigo BETWEEN v_min_clicodigo AND v_max_clicodigo THEN
		SET v_valida = TRUE;
	ELSE
		SET v_valida = FALSE;
	END IF;
    
    RETURN v_valida;
END $$

DELIMITER ;



DROP FUNCTION f_valida_funcodigo;

DELIMITER $$

CREATE FUNCTION f_valida_funcodigo (p_funcodigo INT)
RETURNS BOOLEAN
BEGIN
	DECLARE v_min_funcodigo INT UNSIGNED DEFAULT 0;
    DECLARE v_max_funcodigo INT UNSIGNED DEFAULT 0;
	DECLARE v_valida BOOLEAN DEFAULT false;
    
    SELECT MIN(funcodigo) INTO v_min_funcodigo FROM funcionario;
    SELECT MAX(funcodigo) INTO v_max_funcodigo FROM funcionario;
    
    IF p_funcodigo BETWEEN v_min_funcodigo AND v_max_funcodigo THEN
		SET v_valida = TRUE;
	ELSE
		SET v_valida = FALSE;
	END IF;
    
    RETURN v_valida;
END $$

DELIMITER ;



DROP FUNCTION f_valida_qtde_produto;

DELIMITER $$

CREATE FUNCTION f_valida_qtde_produto(p_procodigo INT, p_itvqtde INT)
RETURNS BOOLEAN
BEGIN
	DECLARE v_valida BOOLEAN DEFAULT FALSE;
    DECLARE v_qtde INT DEFAULT 0;
    
    SELECT prosaldo INTO v_qtde FROM produto
    WHERE procodigo = p_procodigo; 

	IF p_itvqtde <= v_qtde THEN
        SET v_valida = TRUE;
	END IF;  
		SET v_valida = FALSE;
    
    RETURN v_valida;

END $$

DELIMITER ;

SELECT f_valida_qtde_produto();



DROP PROCEDURE sp_vendas ;

DELIMITER $$

CREATE PROCEDURE sp_vendas (
	p_vendata DATE, p_venfilcodigo INT, p_venclicodigo INT, p_venvefuncodigo INT,
	p_itvprocodigo INT, p_itvqtde INT
)
BEGIN
	DECLARE v_vencodigo INT DEFAULT 0;
    DECLARE v_filcodigo BOOLEAN DEFAULT FALSE;
    DECLARE v_venclicodigo BOOLEAN DEFAULT FALSE;
    DECLARE v_venfuncodigo BOOLEAN DEFAULT FALSE;
    DECLARE v_qtde_produto BOOLEAN DEFAULT FALSE;
    
	SELECT f_valida_filcodigo(p_venfilcodigo) INTO v_filcodigo;
	IF v_filcodigo = FALSE THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Filial inexistente';
	END IF;
    
    SELECT f_valida_clicodigo(p_venclicodigo) INTO v_venclicodigo;
    IF v_venclicodigo = FALSE THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Cliente inexistente';
	END IF;
    
    SELECT f_valida_funcodigo (p_venvefuncodigo) INTO v_venfuncodigo;
    IF v_venfuncodigo = FALSE THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Funcionario inexistente';
	END IF;
    
    SELECT f_incrementa_vencodigo() INTO v_vencodigo;
    
    INSERT INTO venda (vencodigo, vendata, venfilcodigo, venclicodigo, venfuncodigo)
    VALUES (v_vencodigo, p_vendata, p_venfilcodigo, p_venclicodigo, p_venvefuncodigo);
    
    SELECT f_valida_qtde_produto(p_itvprocodigo, p_itvqtde) INTO v_qtde_produto;
    
    IF v_qtde_produto = FALSE THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'A quantidade de produtos em estoque é inferior a requerida!';
	END IF;
    
    INSERT INTO itemvenda (itvvencodigo, itvprocodigo, itvqtde)
    VALUES (v_vencodigo, p_itvprocodigo, p_itvqtde);
    
    SELECT vencodigo, vendata, venfilcodigo, venclicodigo, venfuncodigo,
    itvvencodigo, itvprocodigo, itvqtde FROM venda
    INNER JOIN itemvenda ON vencodigo = itvvencodigo
    WHERE vencodigo = v_vencodigo;
    
END $$

DELIMITER ;

CALL sp_vendas ("2025-05-02", 1, 10, 9, 10, 10);

SELECT * FROM venda;
SELECT * FROM itemvenda;
SELECT prosaldo from produto where procodigo = 10;
SELECT COUNT(clicodigo) from cliente;
SELECT clicodigo FROM cliente order by clicodigo;
describe cliente;










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
        
        -- A)
        
        IF v_cont = 1 AND v_texto <> v_texto_maiusculo THEN
			SET v_ocorrencias = CONCAT(v_ocorrencias, v_texto_maiusculo);
            
		ELSEIF SUBSTRING(p_texto, v_cont - 1, 1) = " " THEN
			SET v_ocorrencias = CONCAT(v_ocorrencias, v_texto_maiusculo);
 
		-- B)
        
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







