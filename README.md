<h2>Run Server on another port</h2>
mvnw spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
<h2>Get all players</h2>
curl http://localhost:8081/players
<h2>Send request to football to get Player by id </h2>
curl http://localhost:8081/players/1884823
<h2>Add player</h2>
curl -X POST http://localhost:8081/players -H "Content-Type:application/json" -d '{"jerseyNumber": 1, "name": "TestName", "position":"winger","dateOfBirth":"2023-11-04" }'