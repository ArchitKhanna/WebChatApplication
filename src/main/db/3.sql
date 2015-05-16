use chat;

select * from messages join users on users.id = messages.user_id where users.name = 'Gennady' and date(messages.date) = '2015-05-02';