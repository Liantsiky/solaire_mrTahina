create database solaire;

--tables
	-- panneau
	create table panneau (
		id serial primary key,
		designation varchar(30),
		capacite double precision check (capacite>0)
	);
	
	-- batterie
	create table batterie(
		id serial primary key,
		designation varchar(30),
		capacite double precision check (capacite>0)
	);
	
	--systeme
	create table systeme (
		id serial primary key,
		id_panneau int,
		id_batterie int,
		foreign key (id_panneau) references panneau(id),
		foreign key (id_batterie) references batterie(id)
	);
	
	--classe
	create table classe (
		id serial primary key,
		designation varchar(30),
		id_systeme int,
		foreign key (id_systeme)references systeme(id)
	);
	
	--creneau
	create table creneau (
		id int primary key,
		date_debut time,
		date_fin time
	);
	
	--soleil
	create table soleil (
		id serial primary key,
		id_creneau int,
		puissance double precision,
		date_soleil date,
		foreign key (id_creneau) references creneau(id)
	);
	
	
	--denpense batterie
	create table depense_batterie (
		id serial primary key,
		id_batterie int,
		id_creneau int,
		power_use double precision check (power_use>0),
		foreign key (id_batterie) references batterie(id),
		foreign key (id_creneau) references creneau(id)
	);
	
	-- moment de la journee
	create table moment_journee (
		id serial primary key,
		designation varchar(30),
		heure_debut time,
		heure_fin time
	);
	--presence
	create table presence (
		id serial primary key,
		id_classe int,
		nombre int,
		date_presence date,
		id_moment_journee int,
		foreign key(id_moment_journee) references moment_journee(id)
	);
	
	
	
	
	
--	
	
	
	
	
	
	
	
	
	
	
	
	