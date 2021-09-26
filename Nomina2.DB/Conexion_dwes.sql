alter session set "_ORACLE_SCRIPT"=true;

create user dwes identified by dwes 
default tablespace users 
temporary tablespace temp 
quota 500K on users;

grant create session to dwes;

grant create table to dwes;

