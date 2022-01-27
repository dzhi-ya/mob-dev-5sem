package com.example.lessonfive

import android.util.Log

typealias PersonListener = (person: Person?) -> Unit

data class Person(
    val imgId: Int,
    val title: String,
    val date: String,
    val sex: String,
    val description: String)

object PersonHolder {

    val personsList = mutableListOf<Person>()
    private val myListener = mutableSetOf<PersonListener>()

    init {
        personsList.add(
            Person(
                R.drawable.kolumb,
                "Христофор Колумб",
                "1451-1506",
                "МУЖЧИНА",
                "Испанский мореплаватель итальянского происхождения, в 1492 году открывший для европейцев Новый Свет (Америку)."))
        personsList.add(
            Person(
                R.drawable.leo,
                "Леонардо да Винчи",
                "1452-1519",
                "МУЖЧИНА",
                "Итальянский художник и учёный, изобретатель, писатель, музыкант, один из крупнейших представителей искусства Высокого Возрождения."))
        personsList.add(
            Person(
                R.drawable.lamarr,
                "Хеди Ламарр",
                "1914-2000",
                "ЖЕНЩИНА",
                "Австрийская и американская киноактриса и изобретательница, чья популярность пришлась на 1930-1940-е годы."))
        personsList.add(
            Person(
                R.drawable.mandela,
                "Нельсон Мандела",
                "1918-2013",
                "МУЖЧИНА",
                "Южноафриканский государственный и политический деятель. Активист в борьбе за права человека в период существования апартеида."))
        personsList.add(
            Person(
                R.drawable.gates,
                "Билл Гейтс",
                "1955-...",
                "МУЖЧИНА",
                "Американский предприниматель и общественный деятель, филантроп, один из создателей и бывший крупнейший акционер компании Microsoft."))
        personsList.add(
            Person(
                R.drawable.hokking,
                "Стивен Хокинг",
                "1942-2019",
                "МУЖЧИНА",
                "Английский физик-теоретик, космолог и астрофизик, писатель, директор по научной работе Центра теоретической космологии Кембриджского университета."))
        personsList.add(
            Person(
                R.drawable.durov,
                "Павел Дуров",
                "1984-...",
                "МУЖЧИНА",
                "Российский предприниматель, программист, долларовый миллиардер, один из создателей социальной сети «ВКонтакте» и одноимённой компании, Telegram и других проектов."))
        personsList.add(
            Person(
                R.drawable.dark,
                "Жанна д'Арк",
                "1412-1431",
                "ЖЕНЩИНА",
                "Национальная героиня Франции, одна из командующих французскими войсками в Столетней войне. Осуждена как еретик и сожжена на костре. "))
        personsList.add(
            Person(
                R.drawable.jesus,
                "Иисус Христос",
                "0-33",
                "МУЖЧИНА",
                "Центральная личность в христианстве, которое рассматривает его как предсказанного в Ветхом Завете Мессию, ставшего искупительной жертвой за грехи людей."))
        personsList.add(
            Person(
                R.drawable.putin,
                "Путин Владимир Владимирович",
                "1952-...",
                "МУЖЧИНА",
                "Российский государственный, политический и военный деятель. Действующий Президент Российской Федерации, председатель Государственного Совета Российской Федерации и верховный главнокомандующий Вооружёнными силами Российской Федерации с 7 мая 2012 года."))
    }

    fun addListener(listener: PersonListener) {
        myListener.add(listener)
    }

    fun sendMessage() {
        Log.i("my_tag", "Send")
        for (listener in myListener)
            listener.invoke(personsList.firstOrNull())
        if (personsList.count() > 0)
            personsList.removeAt(0)
    }

}
