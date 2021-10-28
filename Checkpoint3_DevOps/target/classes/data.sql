CREATE TABLE profile(
	id int PRIMARY KEY auto_increment,
	name varchar(200),
	peso double,
	sexo varchar(200),
	raca varchar(200),
	dtnasc varchar(200)
);

INSERT INTO profile VALUES(        
		1,
        'Nina',
        15.0,
        'Feminino',
        'Persa',
        '08/03/2012'
);

INSERT INTO profile VALUES(        
		2,
        'Simba',
        8.5,
        'Masculino',
        'Chartreux',
        '12/06/2019'
);

INSERT INTO profile VALUES(        
		3,
        'Sushi',
        12.2,
        'Masculino',
        'Nebelung',
        '02/08/2017'
);