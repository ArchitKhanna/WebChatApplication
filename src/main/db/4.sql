use chat;

select * from messages join users on users.id = messages.user_id where users.name = 'Shyshpanchik' and messages.text like "%hello%";