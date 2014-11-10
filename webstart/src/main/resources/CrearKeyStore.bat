SET kEY_PATH=.\keyStore.key
SET PASSWORD=versality
SET ALIAS_KEY=versality

keytool -genkey -keystore %kEY_PATH% -alias %ALIAS_KEY% -storepass %PASSWORD% -dname "cn=Versality, ou=VER, o=VER, c=EC"