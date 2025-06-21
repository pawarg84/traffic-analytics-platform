@Service
public class TrafficProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "vehicle_data";
    private final Random random = new Random();

    public TrafficProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private final String[] intersectionIds = {
        "Intersection-1", "Intersection-2", "Intersection-3", 
        "Intersection-4", "Intersection-5", "Intersection-6",
        "Intersection-7", "Intersection-8", "Intersection-9", 
        "Intersection-10"
    };

    public void streamTrafficData() throws InterruptedException {
        System.out.println("✅ Kafka Producer started. Streaming traffic data to topic: " + TOPIC);
        while (true) {
            String intersectionId = intersectionIds[random.nextInt(intersectionIds.length)];
            int vehicleCount = random.nextInt(50);
            long initialTime = System.currentTimeMillis(); 
            long timestamp = initialTime + TimeUnit.HOURS.toMillis(random.nextInt(5));

            String data = String.format(
                "{\"intersectionId\": \"%s\", \"vehicleCount\": %d, \"timestamp\": %d}", 
                intersectionId, vehicleCount, timestamp
            );

            kafkaTemplate.send(TOPIC, data);
            System.out.println("📤 Sent traffic data: " + data);
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
