CREATE SCHEMA questionnare AUTHORIZATION postgres;
-----------------------------------------------------------------------------------------------------
-- Question table
CREATE TABLE questionnare.question (
	id serial NOT NULL,
	question_text varchar NULL,
	category varchar NULL,
	question_type varchar NULL,
	CONSTRAINT question_pk PRIMARY KEY (id)
);

-----------------------------------------------------------------------------------------------------
-- Question option table
CREATE TABLE questionnare."option" (
	id serial NOT NULL,
	option_text varchar NULL,
	question_fk int4 NULL,
	CONSTRAINT option_pk PRIMARY KEY (id)
);

ALTER TABLE questionnare."option" ADD CONSTRAINT option_fk FOREIGN KEY (question_fk) REFERENCES questionnare.question(id);

-----------------------------------------------------------------------------------------------------
-- Number range table
CREATE TABLE questionnare.number_range (
	id serial NOT NULL,
	from_range int4 NOT NULL,
	to_range int4 NOT NULL,
	question_fk int4 NOT NULL,
	CONSTRAINT number_range_pk PRIMARY KEY (id)
);

ALTER TABLE questionnare.number_range ADD CONSTRAINT number_range_fk FOREIGN KEY (question_fk) REFERENCES questionnare.question(id);

-----------------------------------------------------------------------------------------------------
--Conditional questions that keeps related question fk as well
CREATE TABLE questionnare.conditional_question (
	id serial NOT NULL,
	referenced_question_fk int4 NOT NULL,
	predicate varchar NOT NULL,
	referenced_option_fk int4 NULL,
	conditioned_question_fk int4 NOT NULL,
	CONSTRAINT conditional_question_pk PRIMARY KEY (id)
);

ALTER TABLE questionnare.conditional_question ADD CONSTRAINT related_option_fk FOREIGN KEY (referenced_option_fk) REFERENCES questionnare.option(id);
ALTER TABLE questionnare.conditional_question ADD CONSTRAINT related_question_fk FOREIGN KEY (referenced_question_fk) REFERENCES questionnare.question(id);
ALTER TABLE questionnare.conditional_question ADD CONSTRAINT conditional_question_fk FOREIGN KEY (conditioned_question_fk) REFERENCES questionnare.question(id);

-----------------------------------------------------------------------------------------------------
-- Answer table
CREATE TABLE questionnare.answer (
	number_value int4 NULL,
	id serial NOT NULL,
	question_fk int4 NULL,
	option_fk int4 NULL,
	CONSTRAINT answer_pk1 PRIMARY KEY (id)
);

ALTER TABLE questionnare.answer ADD CONSTRAINT answer_fk FOREIGN KEY (option_fk) REFERENCES questionnare.question(id);
ALTER TABLE questionnare.answer ADD CONSTRAINT option_fk FOREIGN KEY (option_fk) REFERENCES questionnare.option(id);








