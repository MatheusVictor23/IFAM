show databases;

use bd2025;

describe funcionario;

alter table funcionario
add fundtadmissão date;

alter table funcionario 
add funsexo char(1);

create table entregador (
entfuncodigo int not null,
primary key(entfuncodigo),
foreign key (entfuncodigo) references funcionario(funcodigo)
);

alter table venda
add venentfuncodigo int,
add foreign key (venentfuncodigo) references entregador (entfuncodigo);

/* Questão 16*/

select distinct * from produto
inner join itemvenda on procodigo = itvprocodigo
inner join venda on vencodigo = itvvencodigo
inner join cliente on clicodigo = venclicodigo  /* (T1 |X| T2) |X| T3) |X| T4) inner join é uma operação binária*/
inner join estadocivil on estcodigo = cliestcodigo
where proativo = 1 and prosaldo > 5 
and estdescricao = 'casado' and clisexo = 'f';


/* Questão 15 */

update funcionario set fundtdem = '2023-03-21'
where funcondigo = 1;

-- Questão 18
select * from funcionario where fundtdem is not null;



-- Diferenciar campos da mesma tabela e fazer um join entre.

select ger.funcodigo 'codgerente', ger.funnome 'Nome gerente',
sub.funcodigo 'Sub codigo', sub.funnome 'Sub nome' from funcionario ger
inner join funcionario sub on ger.funcodigo = sub.funcodgerente;


select  ger.funnome 'nome gerente', count(*) 'total subordinados'
from funcionario ger
inner join funcionario sub on ger.funcodigo = sub.funcodgerente
group by ger.funnome
having count(*) > 6;



--


select funnome "Funcionario", funsalario "Salário", bainome "Bairro", zonnome "Zona" from funcionario inner join bairro on baicodigo = funbaicodigo
inner join zona on zoncodigo = baizoncodigo where fundtdem is null
order by funcodigo;

/* Questão 27*/

select sum(funsalario)
from funcionario inner join bairro on funbaicodigo = baicodigo
inner join zona on baizoncodigo = zoncodigo
group by zonnome;

select zonnome 'Zona', sum(funsalario) 'Total zona'
from funcionario inner join bairro on funbaicodigo = baicodigo
inner join zona on baizoncodigo = zoncodigo
group by zonnome;

select zonnome 'Zona', sum(clirendamensal) 'Total zona'
from cliente inner join bairro on clibaicodigo = baicodigo
inner join zona on baizoncodigo = zoncodigo
group by zonnome
having sum(clirendamensal) > 20000;

select zonnome 'Zona', clisexo 'Sexo', sum(clirendamensal) 'Total zona'
from cliente inner join bairro on clibaicodigo = baicodigo
inner join zona on baizoncodigo = zoncodigo
group by zonnome, clisexo
having sum(clirendamensal) > 20000;

select zonnome 'Zona', clisexo 'Sexo', sum(clirendamensal) 'Total zona', cliestcodigo 'Estado civil'
from cliente inner join bairro on clibaicodigo = baicodigo
inner join zona on baizoncodigo = zoncodigo
inner join estadocivil on cliestcodigo = estcodigo
group by zonnome, clisexo, cliestcodigo
having sum(clirendamensal) > 20000;

select zonnome 'Zona', clisexo 'Sexo', sum(clirendamensal) 'Total zona', estDescricao'Estado civil'
from cliente inner join bairro on clibaicodigo = baicodigo
inner join zona on baizoncodigo = zoncodigo
inner join estadocivil on cliestcodigo = estcodigo
group by zonnome, clisexo, estDescricao
having sum(clirendamensal) > 20000;

select count(*) from cliente;
select count(*) from bairro;

/*join*/
select * from cliente, bairro where baicodigo = clibaicodigo;


--
select * from bairro;

select baicodigo from bairro order by baicodigo;
select distinct clibaicodigo from cliente;

-- Outer join

select baicodigo, bainome, cliente.* from bairro 
left outer join cliente on baicodigo = clibaicodigo;

select baicodigo, bainome, cliente.* from cliente 
left outer join bairro on baicodigo = clibaicodigo;


-- produtos que não foram vendidos
select * from produto;
select * from itemvenda;

select * from produto inner join itemvenda on procodigo != itvprocodigo;

select pronome from produto 
left outer join itemvenda on procodigo = itvprocodigo
where itvvencodigo is null;


/*Formas normais e dependência funcional*/    