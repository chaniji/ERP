package router

import (
	"notification-service/handler"

	"github.com/gin-gonic/gin"
)

func SetupRouter() *gin.Engine {
	r := gin.Default()

	r.GET("/health", handler.HealthCheck)
	r.POST("/api/notify/email", handler.ManualEmail)

	return r
}
