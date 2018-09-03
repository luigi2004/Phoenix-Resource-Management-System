create table ResourceType(
	retID numeric PRIMARY KEY,
	retName VARCHAR(20) unique,
	retIconName VARCHAR(25) not null,
	retDescr VARCHAR(255)
);
create table Resources(
	resId numeric primary key,
	roomNumber numeric not null,
	resName varchar(20) not null,
	resType numeric references ResourceType(retID),
	description varchar(255) not null,
	status varchar(10) not null,
	photoOfResource varchar(15)
);

create table Location(
	locId numeric primary key,
	locName varchar(20) unique,
	locCity varchar(20) not null,
	locState varchar(20) not null,
	locAddress varchar(50) not null,
	description varchar(255) not null
);

create table LocationResource(
	locResId numeric primary key,
	resId numeric references Resources(resId),
	locId numeric references Location(locId),
	description varchar(255) not null
);

create table Feature(
	feaId numeric primary key,
	feaType varchar(20) unique,
	description varchar(255) not null,
	iconPath varchar(50) not null
);

create table ResourceFeature(
	resFeaId numeric primary key,
	feaId numeric references Feature(feaId),
	locResId numeric references LocationResource(locResId),
	quantity numeric not null,
	description varchar(255) not null
);

-----------------

create table Users(
	userId numeric primary key,
	userName varchar(20) not null,
	userEmail varchar(30) unique,
	userPassword varchar(20) not null,
	userType varchar(20) not null,
	userPhone varchar(10) not null,
	locId numeric references Location(locId)
);

create table Booking(
	bookingId numeric primary key,
	locResId numeric references LocationResource(locResId),
	userId numeric references Users(userId),
	startTime timestamp not null,
	endTime timestamp not null,
	description varchar(255) not null
);

create table Visitor(
	visId numeric primary key,
	visName varchar(20) not null,
	visEmail varchar(30) not null,
	visPhone varchar(10) not null,
	badgeId numeric unique,
	visPurpose varchar(50) not null,
	visCompany varchar(50) not null
);

create table Invitation(
	invId numeric primary key,
	userId numeric references Users(userId),
	bookingId numeric references Booking(bookingId),
	status varchar(10) not null,
	visId numeric references Visitor(visId),
	description varchar(255) not null
);





