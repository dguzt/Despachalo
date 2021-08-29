# Despachalo API

# Deployment
## 1. Heroku
Login into Heroku CLI

Create an app using a creative name:
```bash
heroku create despachalo
```

Change the default task for `assemble` to avoid tests execution:
```bash
heroku config:set GRADLE_TASK="assemble"
```

Push the code to Heroku:
```bash
git push heroku master
```

Ensure one web instance for the app:
```bash
heroku ps:scale web=1
```

Check the logs:
```bash
heroku logs --tail
```
