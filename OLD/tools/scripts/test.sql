--query 1 works
select r.resid
from resources r join locationresource lr
on r.resid = lr.resid where lr.locid = 2;

select * from locationresource;

--query 2 works
select b.bookingid
from booking b 
join locationresource lr
on b.locresid = lr.locresid
where (lr.locid = 1 or lr.resid = 2) 
and ((b.starttime between TO_TIMESTAMP('10-AUG-18','DD-MON-RR')
    and TO_TIMESTAMP('20-AUG-18','DD-MON-RR')) 
    or (b.endtime between TO_TIMESTAMP('10-AUG-18','DD-MON-RR') 
        and TO_TIMESTAMP('20-AUG-18','DD-MON-RR')));

--query 3
select r.resid 
from resources r
join locationresource lr
on r.resid = lr.resid
join booking b
on lr.locresid = b.locresid
where r.status = 'booked' 
and r.roomnumber = 12
and (b.starttime >= TO_TIMESTAMP('20-AUG-1800:00:00.000000','DD-MON-RRHH24:MI:SS.FF') 
    and b.endtime < TO_TIMESTAMP('21-AUG-1800:00:00.000000','DD-MON-RRHH24:MI:SS.FF'));

--query 4
select f.feaid,f.featype,f.description,f.iconpath from feature f 
join ResourceFeature rf on f.feaid = rf.feaid 
join LocationResource lr on rf.locresid = lr.locresid
where lr.locid = 1 or lr.resid = 6;

--query 5
select r.resid
from resources r
join locationresource lr
on r.resid = lr.resid
join booking b
on lr.locresid = b.locresid
where ((b.starttime between TO_TIMESTAMP('10-AUG-18','DD-MON-RR') and ?) or (b.endtime between ? and ?))
and lr.resid = ? and lr.locid =?;

select starttime, endtime
from booking;

commit;
--query 6
select b.bookingid
from booking b
join users u
on b.userid = u.userid
where b.userid = ?
and ((b.starttime between ? and ?) or (b.endtime between ? and ?));

select *
from booking
where starttime between TO_TIMESTAMP('10-AUG-18','DD-MON-RR') 
and TO_TIMESTAMP('20-AUG-18','DD-MON-RR');

select *
from booking
where starttime > TO_TIMESTAMP('01-AUG-18','DD-MON-RR') 
and endtime < TO_TIMESTAMP('29-AUG-18','DD-MON-RR');

--query 7
select i.userid, i.visid
from invitation i
where i.bookingid = 1;

--query 7 but with visname
select i.userid, v.visname
from invitation i
join visitor v
on i.visid = v.visid
where i.bookingid = 1;

select * from invitation;
select * from visitor;