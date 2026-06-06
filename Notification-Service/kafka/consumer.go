package kafka

import (
	"context"
	"encoding/json"
	"log"
	"notification-service/email"
	"notification-service/model"

	"github.com/segmentio/kafka-go"
)

func StartConsumer(ctx context.Context, brokers []string, topic string, groupID string) {
	reader := kafka.NewReader(kafka.ReaderConfig{
		Brokers:  brokers,
		GroupID:  groupID,
		Topic:    topic,
		MinBytes: 10e3, // 10KB
		MaxBytes: 10e6, // 10MB
	})

	defer reader.Close()

	for {
		m, err := reader.ReadMessage(ctx)
		if err != nil {
			log.Printf("Error reading message: %v\n", err)
			break
		}

		var n model.Notification
		if err := json.Unmarshal(m.Value, &n); err != nil {
			log.Printf("Error unmarshaling message: %v\n", err)
			continue
		}

		log.Printf("Received Notification: %+v\n", n)
		email.SendEmail(n.Email, "System Notification", n.Message)
	}
}
