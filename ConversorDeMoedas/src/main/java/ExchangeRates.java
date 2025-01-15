import java.util.Map;

public class ExchangeRates {
    private String base_code;
    private Map<String, Double> conversion_rates;

    // Método para obter a taxa de conversão entre duas moedas
    public double getRate(String targetCurrency, String baseCurrency) {
        if (conversion_rates != null) {
            if (baseCurrency.equals(base_code)) {
                return conversion_rates.getOrDefault(targetCurrency, 0.0);
            } else {
                throw new RuntimeException("Moeda base não corresponde aos dados obtidos.");
            }
        }
        throw new RuntimeException("Dados de taxas de conversão não disponíveis.");
    }

    // Getters e Setters
    public String getBaseCode() {
        return base_code;
    }

    public void setBaseCode(String base_code) {
        this.base_code = base_code;
    }

    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }

    public void setConversionRates(Map<String, Double> conversion_rates) {
        this.conversion_rates = conversion_rates;
    }
}
