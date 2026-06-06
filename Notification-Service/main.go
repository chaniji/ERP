package main

import (
	"context"
	"log"
	"notification-service/kafka"
	"notification-service/router"
	"os"
)

func main() {
	brokers := []string{getEnv("KAFKA_BROKERS", "localhost:9092")}
	
	// Start Kafka Consumers in goroutines
	ctx := context.Background()
	go kafka.StartConsumer(ctx, brokers, "employee-events", "notification-group")
	go kafka.StartConsumer(ctx, brokers, "inventory-events", "notification-group")
	go kafka.StartConsumer(ctx, brokers, "payroll-events", "notification-group")
	go kafka.StartConsumer(ctx, brokers, "auth-events", "notification-group")

	// Start Gin Server
	r := router.SetupRouter()
	port := getEnv("PORT", "8085")
	log.Printf("Notification Service starting on port %s\n", port)
	if err := r.Run(":" + port); err != nil {
		log.Fatalf("Failed to run server: %v\n", err)
	}
}

func getEnv(key, fallback string) string {
	if value, ok := os.LookupEnv(key); ok {
		return value
	}
	return fallback
}
