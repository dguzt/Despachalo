# Despachalo API

## Env Variables
1. Generate the JWT secret key. Then, store it in the `.env` file:
    ```bash
    # In terminal
    ./gradlew genJtwKey -q
    # secret key ==> T05nwOMsphgZ9J1AUD+s0YMHiLO/SY7ea6qkoYBiAws=
    ```
    ```properties
    # .env
    DESPACHALO_SECURITY_JWT=T05nwOMsphgZ9J1AUD+s0YMHiLO/SY7ea6qkoYBiAws=
    ```

## Deployment
### 1. Heroku
Login into Heroku CLI

Create an app using a creative name like `despachalo`:
```bash
heroku create despachalo
```

Change the default task for `assemble` to avoid tests execution:
```bash
heroku config:set GRADLE_TASK="assemble"
```

Create a database:
```bash
heroku addons:create heroku-postgresql
heroku config # copy DATABASE_URL variable
heroku config:set SPRING_DATASOURCE_URL= # paste DATABASE_URL value here
```

Push the code to Heroku and ensure one web instance for the app:
```bash
git push heroku master
heroku ps:scale web=1
```

Check the logs:
```bash
heroku logs --tail
```
