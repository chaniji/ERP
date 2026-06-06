package email

import (
	"log"
)

func SendEmail(to string, subject string, body string) error {
	// Mock email sending. Logic for actual SMTP/Mail-CLI can be added.
	log.Printf("Sending Email To: %s | Subject: %s | Body: %s\n", to, subject, body)
	return nil
}
