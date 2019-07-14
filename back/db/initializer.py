def create(cursor):
    createUsers = """CREATE TABLE IF NOT EXISTS users (
        id        INT(11) PRIMARY KEY AUTO_INCREMENT,
        name      VARCHAR(60) NOT NULL,
        email     VARCHAR(60) NOT NULL,
        password  VARCHAR(60) NOT NULL
        ); """

    createTokens = """CREATE TABLE IF NOT EXISTS tokens (
        id      INT(11) PRIMARY KEY,
        email   VARCHAR(60) NOT NULL,
        token   VARCHAR(60) NOT NULL,
        date    VARCHAR(60) NOT NULL
        ); """

    createCarpools = """CREATE TABLE IF NOT EXISTS carpools (
        id             INT(11) PRIMARY KEY AUTO_INCREMENT,
        driverId       INT(11) NOT NULL,
        driverName     VARCHAR(60) NOT NULL,
        time           VARCHAR(60) NOT NULL,
        capacity       INT(11) NOT NULL,
        capacityLeft   INT(11) NOT NULL,
        neighbourhood  VARCHAR(60) NOT NULL,
        type           INT(11) NOT NULL,
        fee            INT(11) NOT NULL
        );
        """

    cursor.execute(createTokens)
    cursor.execute(createUsers)
    cursor.execute(createCarpools)

