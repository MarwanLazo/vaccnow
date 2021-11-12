docker rm -f vaccnow_app_1 vaccnow_app-db_1

docker rmi -f vaccnow_app

docker-compose -f -d .\docker-compose.yml up