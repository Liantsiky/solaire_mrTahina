 create function f_getMoyenneparJour(dayweek int)
    returns table(id_systeme int,moyenne bigint,id_moment_journee int,heure_debut time,heure_fin time)
    language plpgsql
    as 
    $$
    begin
        return query 
        select 
			p.id_systeme,
			sum(p.nombre)/count(distinct(p.date_presence)),
			p.id_moment_journee,
			m.heure_debut,
			m.heure_fin
		from v_presence_classe as p join moment_journee m on p.id_moment_journee=m.id
		where extract(dow from date_presence)=dayweek
		group by p.id_systeme,p.id_moment_journee,m.heure_debut,m.heure_fin;
    end;
    $$;
    
--