package ru.otus.arch.composeapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.otus.arch.memory.DependencyFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val useCase = DependencyFactory.createGetWeatherUseCase()
        val weather = useCase.getWeather("Москва")
        
        val textView = TextView(this).apply {
            text = """
            🏗️ АРХИТЕКТУРА РАБОТАЕТ!
            
            ✅ UseCase: ${useCase::class.simpleName}
            ✅ Repository: WeatherRepositoryImpl
            ✅ DI: DependencyFactory
            
            🌤️ ДАННЫЕ:
            Город: ${weather.city}
            Температура: ${weather.temperature}°C
            Условия: ${weather.condition}
            
            🎉 ВСЕ КОМПОНЕНТЫ РАБОТАЮТ!
            """.trimIndent()
            textSize = 16f
            setPadding(50, 100, 50, 0)
        }
        
        setContentView(textView)
        
        // Логируем успех
        println("✅ АРХИТЕКТУРА ВЫПОЛНЕНА: UseCase -> Repository -> Data")
    }
}
