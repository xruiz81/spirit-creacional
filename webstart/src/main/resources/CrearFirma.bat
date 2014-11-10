SET kEY_PATH=.\keyStore.key
SET PASSWORD=versality
SET ALIAS_KEY=versality

keytool -keystore %kEY_PATH% -storepass %PASSWORD% -selfcert -alias %ALIAS_KEY%  -dname "cn=Versality, ou=VER, o=VER, c=EC"