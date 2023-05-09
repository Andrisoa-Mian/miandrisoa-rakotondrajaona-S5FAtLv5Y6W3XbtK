# miandrisoa-rakotondrajaona-S5FAtLv5Y6W3XbtK
C'est un projet test Spring Boot + Spring Security + JWT

# Connection via CURL
Connecter par user/password paire

curl -X POST http://localhost:8081/auth/signin -H "Content-Type:application/json" -d "{\"username\":\"user\", \"password\":\" mot de passe\"}"
{
"username" : "user",
"token" : "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTUyNDY0OTI4OSwiZXhwIjoxNTI0NjUyODg5fQ.Lj1w6vPJNdJbcY6cAhO3DbkgCAqpG7lzztzUeKMyNyE"
}

1- Mettez la valeur du jeton dans l'en-tête HTTP Authorization, définissez sa valeur sur Bearer token, puis accédez aux informations de l'utilisateur actuel.
2- curl -X GET http://localhost:8082/etudiant/rechercher?classe=cp2&enseignant=Isabelle marine
"Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTUyNDY0OTI4OSwiZXhwIjoxNTI0NjUyODg5fQ.Lj1w6vPJNdJbcY6cAhO3DbkgCAqpG7lzztzUeKMyNyE"
