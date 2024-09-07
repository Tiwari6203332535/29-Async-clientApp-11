package in.happy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class Application {
	static String url="http://13.232.253.164:8080/ticket/{ticketNum}";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		WebClient webClient = WebClient.create();
		System.out.println("Request sending start......");
		webClient.get()
                   .uri(url,6)
                   .header("Accept","application/json")
                   .retrieve()
                   .bodyToMono(String.class)
                   
                // .block();            //sync call output is only request started(wait)
                   
		           .subscribe(Application::handleResponse);   //output is request start and end(dont wait)
		
                // .subscribe();      // async call -if we dont want to handle response only to send the request
                  
		System.out.println("Request send is ended");
	}

	private static void handleResponse(String string1) {
		System.out.println(string1);
	}

}
