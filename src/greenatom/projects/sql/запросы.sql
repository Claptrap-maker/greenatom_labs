-- 1 запрос.
select * from t_sportsman;
-- 2. запрос ?
 select td.discipline_description, td.world_record
 from t_discipline td, t_competitions_disciplines tcd
 where tcd.discipline_id = td.discipline_id;
-- 3. запроc
 select sportsman_name
 from t_sportsman
 where year_of_birth = 1990;
-- 4. запрос
 select td.discipline_description, td.world_record
 from t_discipline td, t_competitions_disciplines tcd
 where tcd.discipline_id = td.discipline_id
 and set_date in ('2010-05-12', '2010-05-15');
-- 5. запрос
select tc.hold_date
from t_competition tc, t_result tr 
where tc.city = 'Москва'
and tr.competition_id = tc.competition_id 
and tr.result = 10;
-- 6. запрос
select ts.sportsman_name
from t_sportsman ts, t_sportsman_record tsr
where ts.sportsman_id = tsr.sportsman_id 
and tsr.record_value != 25;
-- 7. запрос
select discipline_description 
from t_discipline 
where world_record = 15
and set_date != '2015-02-12';
-- 8. запрос
select distinct tc.city 
from t_competition tc, t_result tr 
where tc.competition_id = tr.competition_id
and tr.result in (13, 25, 17, 9);
-- 9. запрос
select sportsman_name 
from t_sportsman
where year_of_birth = 2000
and not rank in (3, 7, 9); 
-- 10. запрос
select hold_date 
from t_competition
where city like 'М%';
-- 11. запрос
select sportsman_name 
from t_sportsman
where sportsman_name like 'M%'
and not year_of_birth % 10 = 6;
-- 12. запрос
select competition_name 
from t_competition
where competition_name like '%международные%';
-- 13. запрос
select distinct year_of_birth 
from t_sportsman;
-- 14. запрос
select count(tr."result")
from t_result tr, t_competition tc 
where tr.competition_id = tc.competition_id 
and tc.hold_date = '2014-05-12';
-- 15. запрос
select max(tr."result")
from t_result tr, t_competition tc 
where tr.competition_id = tc.competition_id 
and tc.city = 'Москва';
-- 16. запрос
select min(year_of_birth)
from t_sportsman
where rank = 1;
-- 17. запрос
select ts.sportsman_name 
from t_sportsman ts, t_sportsman_record tsr, t_result tr, t_competition tc
where ts.sportsman_id = tsr.sportsman_id 
and tr.sportsman_id = ts.sportsman_id 
and tr.result = tsr.record_value
and tr.competition_id = tc.competition_id 
and tsr.discipline_id = tr.discipline_id 
and tc.hold_date = '2014-04-12';
-- 18. запрос
select td.discipline_description 
from t_discipline td, t_competitions_disciplines tcd, t_competition tc 
where td.discipline_id = tcd.discipline_id 
and tc.competition_id = tcd.competition_id 
and td.set_date = '2015-04-20'
and tc.city = 'Москва';
-- 19. запрос
select ts.sportsman_name, avg(tsr.record_value) filter
(where ts.sportsman_id = tsr.sportsman_id)
from t_sportsman ts, t_sportsman_record tsr
group by ts.sportsman_id;
-- 20. запрос 1 вариант
with func as (
	select avg(tr."result") filter
	(where ts.sportsman_id = tr.sportsman_id) as avg_each_sportsman
	from t_sportsman ts, t_result tr
	group by ts.sportsman_id
)
select ts2.year_of_birth  
from t_competition tc, func, t_sportsman ts2, t_result tr2
where ts2.sportsman_id = tr2.sportsman_id  
and tc.competition_id = tr2.competition_id  
and tc.city = 'Москва'
group by ts2.year_of_birth, tr2."result" 
having tr2."result" > avg(avg_each_sportsman);
-- 2ой вариант
select ts.year_of_birth 
from t_sportsman ts, t_result tr, t_competition tc
where ts.sportsman_id = tr.sportsman_id 
and tc.competition_id = tr.competition_id 
and tc.city = 'Москва'
and tr.result > 
(select avg(tr2."result")
from t_result tr2);
-- 21. запрос
select sportsman_name 
from t_sportsman
group by sportsman_name, year_of_birth
having year_of_birth >  
(select date_part('year', set_date::date) 
from t_discipline
where world_record = 12);
-- 22. запрос
select 'Спортсмен', sportsman_name, 'показал результат', "result", 'в городе', city
from t_sportsman ts, t_result tr, t_competition tc
where ts.sportsman_id = tr.sportsman_id 
and tc.competition_id = tr.competition_id;
-- 23. запрос
select ts1.sportsman_name 
from t_sportsman ts1
where ts1.rank < 
(select avg(ts2."rank") 
from t_sportsman ts2
where ts2.year_of_birth = 2000);
-- 24. запрос
select sportsman_name, "rank", year_of_birth, country
from t_sportsman ts, t_sportsman_record tsr, t_discipline td
where ts.sportsman_id = tsr.sportsman_id 
and tsr.discipline_id = td.discipline_id 
and tsr.record_value = td.world_record;
-- 25. запрос
select count(distinct ts.sportsman_id)
from t_sportsman ts, t_result tr, t_competition tc
where ts.sportsman_id = tr.sportsman_id 
and tr.competition_id = tc.competition_id 
and ts.sportsman_name like '%Ivanov%'
and tc.competition_name like '%Региональные%';
-- 26. запрос
select city 
from t_competition tc, t_result tr, t_discipline td
where tc.competition_id = tr.competition_id 
and td.discipline_id = tr.discipline_id
and tr.result = td.world_record;
-- 27. запрос
select min("rank")
from t_sportsman ts, t_result tr, t_discipline td 
where ts.sportsman_id = tr.sportsman_id
and td.discipline_id = tr.discipline_id 
and tr.result = td.world_record;
-- 28. запрос
with func as (
	select tc.competition_id, tc.competition_name as c_name, count(td.world_record) 
	filter (where td.set_date = tc.hold_date) as count_result
	from t_discipline td, t_competition tc
	group by tc.competition_id
)
select c_name 
from func
group by c_name, count_result
order by count_result desc
limit 1; 
-- 29. запрос
with func as (
	select distinct ts.country as _country, count(ts.sportsman_id) 
	filter (where ts.sportsman_id = tr.sportsman_id) as count_sportsmen
	from t_sportsman ts, t_result tr
	group by ts.country
)
select _country
from func
order by count_sportsmen desc
limit 1; 
-- 30. запрос
update t_sportsman ts set "rank" = "rank" + 1
from t_sportsman_record tsr, t_discipline td
where ts.sportsman_id = tsr.sportsman_id 
and tsr.discipline_id = td.discipline_id 
and tsr.record_value = td.world_record;
-- 31. запрос
select distinct ts.year_of_birth  
from t_competition tc, t_sportsman ts, t_result tr
where ts.sportsman_id = tr.sportsman_id  
and tc.competition_id = tr.competition_id  
and tc.city = 'Москва';
-- 32. запрос
update t_competition tc set hold_date = hold_date + interval '4 day' 
from t_result tr 
where tc.city = 'Москва'
and tr.competition_id = tc.competition_id;
-- 33. запрос
update t_sportsman set country = 'Russia'
where "rank" in (1, 2)
and country = 'Italy';
-- 34. запрос
update t_discipline set discipline_description = 
replace(discipline_description, 'Бег с препятствиями', 'Бег');
-- 35. запрос
update t_discipline set world_record = world_record + 2
where set_date < '2005-03-20';
-- 36. запрос
update t_result tr set "result" = "result" - 2
from t_competition tc
where tr.competition_id = tc.competition_id 
and tc.hold_date = '2012-05-20'
and "result" >= 45;
-- 37. запрос
delete from t_result tr
using t_competition tc, t_sportsman ts
where tr.competition_id = tc.competition_id
and tr.sportsman_id = ts.sportsman_id
and tc.city = 'Москва'
and ts.year_of_birth <= 1980;
-- 38. запрос
delete from t_competition tc
using t_result tr
where tc.competition_id = tr.competition_id
and tr.result = 20;
-- 39. запрос
delete from t_sportsman_record tsr
using t_sportsman ts
where tsr.sportsman_id = ts.sportsman_id
and ts.year_of_birth = 2000;