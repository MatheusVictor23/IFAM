show databases;

use information_schema;

show tables;

select * from schemata;

select * from tables
where table_schema = 'bd2025';

select * from columns
where table_schema = 'bd2025';

select * from routines
where routine_schema = 'bd2025';

select * from triggers;

select * from key_column_usage;

select * from check_constraints;

select * from table_constraints
where constraint_schema = 'bd2025';


select * from table_constraints
where table_name = 'cliente';




select constraint_schema as 'banco', referenced_table_name as 'tabela', column_name as 'campo',
table_name as 'relacionamento', referenced_column_name as 'campo relacionamento'
from key_column_usage
where constraint_schema = 'bd2025' and referenced_table_name = 'cliente'

union

select constraint_schema as 'banco', table_name as 'tabela', column_name as 'campo',
referenced_table_name as 'relacionamento', referenced_column_name as 'campo relacionamento'
from key_column_usage
where constraint_schema = 'bd2025' and table_name = 'cliente';





create database catalogo;
use catalogo;

delimiter $$
create procedure sp_tabelas_relacionadas (p_bd varchar(255), p_table varchar(255))
begin
	select constraint_schema as 'banco', referenced_table_name as 'tabela', column_name as 'campo',
	table_name as 'relacionamento', referenced_column_name as 'campo relacionamento'
	from information_schema.key_column_usage
	where constraint_schema = p_bd and referenced_table_name = p_table

	union

	select constraint_schema as 'banco', table_name as 'tabela', column_name as 'campo',
	referenced_table_name as 'relacionamento', referenced_column_name as 'campo relacionamento'
	from information_schema.key_column_usage
	where constraint_schema = p_bd and table_name = p_table;
end $$
delimiter ;


call sp_tabelas_relacionadas('bd2025', 'cliente');