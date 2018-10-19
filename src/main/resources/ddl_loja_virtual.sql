--------------------------------------------------------
--  DDL for LOJA VIRTUAL
--------------------------------------------------------
--
CREATE TABLE TB_CLIENTE (
  ID_CLIENTE NUMBER NOT NULL
, NOME VARCHAR2(255) NOT NULL
, DOCUMENTO VARCHAR2(255) NOT NULL
, EMAIL VARCHAR2(255) 
, PRIMARY KEY (ID_CLIENTE)
); 
--
CREATE SEQUENCE CLIENTE_SEQ
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
--
CREATE TABLE TB_PRODUTO (
  ID_PRODUTO NUMBER NOT NULL
, NOME VARCHAR2(255) NOT NULL
, PRECO NUMBER NOT NULL
, QUANTIDADE NUMBER 
, PRIMARY KEY (ID_PRODUTO)); 
--
CREATE SEQUENCE PRODUTO_SEQ
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
--
CREATE TABLE TB_CARRINHO (
  ID_CARRINHO NUMBER NOT NULL
, ID_CLIENTE NUMBER NOT NULL
, ID_PRODUTO NUMBER NOT NULL
, QUANTIDADE NUMBER 
, PRIMARY KEY (ID_CARRINHO)
, CONSTRAINT FK_CARRINHO_CLIENTE FOREIGN KEY (ID_CLIENTE) REFERENCES TB_CLIENTE (ID_CLIENTE)
, CONSTRAINT FK_CARRINHO_PRODUTO FOREIGN KEY (ID_PRODUTO) REFERENCES TB_PRODUTO (ID_PRODUTO)
); 
--
CREATE SEQUENCE CARRINHO_SEQ
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
--
COMMIT;
