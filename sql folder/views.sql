-- join presence and moment journee
create or replace view v_presence_moment_journee as 
	select  p.id id_presence,
			p.id_classe id_classe, 
			p.nombrenombre, 
			p.id_moment_journee id_moment_journee,
			m.heure_debut heure_debut,
			m.heure_fin heure_fin
		from presence p join moment_journee as m on p.id_moment_journee=m.id
		group by id_classe,id_moment_journee,id_presence,heure_debut,heure_fin;
	
--join presence and classe
create or replace view v_presence_classe as 
	select  p.id id_presence,
			p.date_presence,
			p.id_classe id_classe, 
			p.nombre, 
			p.id_moment_journee id_moment_journee,
			c.id_systeme id_systeme
		from presence p join classe c on p.id_classe=c.id
		group by id_classe,c.id_systeme,date_presence,id_moment_journee,id_presence;
		
--test
create or replace view v_moyenne as 
	select 
			id_systeme,
			sum(nombre)/count(distinct(date_presence)), 
			id_moment_journee
		from v_presence_classe where extract(dow from date_presence)=1
		group by id_systeme,id_moment_journee;
	
		
		
		
		
		
		
		
		