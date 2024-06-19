from confluent_kafka import Producer
from confluent_kafka.serialization import StringSerializer
import json
import uuid


class SendKafkaMessage():
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.boostrap_servers = "0.0.0.0:29092"
        self.topic = "notification"
        self.serializer = StringSerializer("utf-8")

    def on_start(self):
        self.producer = Producer({
            "bootstrap.servers": self.boostrap_servers,
            "acks":"all"
        })
    def on_stop(self):
        self.producer.flush()

    def send_message(self):
        str_notificatoin = str(uuid.uuid4());
        message = {"notification": "simple notification " + str_notificatoin}
        self.producer.produce(self.topic, key="consuming", value=json.dumps(message))
        self.on_stop()
        print("New notfification is send ", str_notificatoin)

    def teardown(self):
        self.producer.flush()
        self.producer.close()


if __name__ == "__main__":
    for i in range(100):
        kafka_producer = SendKafkaMessage()
        kafka_producer.on_start()
        kafka_producer.send_message()