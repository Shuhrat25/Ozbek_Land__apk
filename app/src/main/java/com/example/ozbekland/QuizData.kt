package com.example.ozbekland

// Тип вопроса
sealed class Question(
    val id: Int,
    val text: String,
    val imageResId: Int? = null      // <-- ресурс картинки (может быть null)
) {
    class InputQuestion(
        id: Int,
        text: String,
        val correctAnswer: String,
        imageResId: Int? = null
    ) : Question(id, text, imageResId)

    class ChoiceQuestion(
        id: Int,
        text: String,
        val options: List<String>,
        val correctAnswer: String,
        imageResId: Int? = null
    ) : Question(id, text, imageResId)
}



// Тут храним все вопросы
object QuizRepository {

    val questions: List<Question> = listOf(

        // 1-QISM: Savol-javob (10 ta) – bu yerda men javoblarni to‘ldirdim
        Question.InputQuestion(
            id = 1,
            text = "Lotin yozuviga asoslangan o’zbek alifbosida nechta harf bor?",
            correctAnswer = "29"
        ),
        Question.InputQuestion(
            id = 2,
            text = "O’zbek alifbosida nechta tovush bor?",
            correctAnswer = "30"
        ),
        Question.InputQuestion(
            id = 3,
            text = "Unli harflar qaysilar?",
            correctAnswer = "a, e, i, o, u, o‘"
        ),
        Question.InputQuestion(
            id = 4,
            text = "Undosh harflar qaysilar?",
            correctAnswer = "b, d, f, g, h, j, k, l, m, n, p, q, r, s, t, v, x, y, z, g‘, sh, ch, ng"
        ),
        Question.InputQuestion(
            id = 5,
            text = "Harf birikmasi bilan ifodalanuvchi undoshlar qaysilar?",
            correctAnswer = "sh, ch, ng"
        ),
        Question.InputQuestion(
            id = 6,
            text = "Qaysi harf ikkita tovush ifodalaydi?",
            correctAnswer = "j"
        ),
        Question.InputQuestion(
            id = 7,
            text = "Qaysi harf so‘z boshida kelmaydi?",
            correctAnswer = "ng"
        ),
        Question.InputQuestion(
            id = 8,
            text = "Jarangli undoshlarga qaysilar kiradi?",
            correctAnswer = "b, d, g, j, z, v, g‘, l, m, n, r, y, ng"
        ),
        Question.InputQuestion(
            id = 9,
            text = "Jarangsiz undoshlarga qaysilar kiradi?",
            correctAnswer = "p, t, k, q, f, s, x, h, ch, sh"
        ),
        Question.InputQuestion(
            id = 10,
            text = "Tovushlarning yozuvdagi ifodasi nima deyiladi?",
            correctAnswer = "harf"
        ),

        // 2-qism: Ma’nodosh so‘zlar (juftini topish)
        Question.InputQuestion(
            id = 11,
            text = "“Chiroyli” so‘zining ma’nodoshini toping.",
            correctAnswer = "go‘zal"
        ),
        Question.InputQuestion(
            id = 12,
            text = "“Tinch” so‘zining ma’nodoshini toping.",
            correctAnswer = "osuda"
        ),
        Question.InputQuestion(
            id = 13,
            text = "“Aqlli” so‘zining ma’nodoshini toping.",
            correctAnswer = "dono"
        ),
        Question.InputQuestion(
            id = 14,
            text = "“Dangasa” so‘zining ma’nodoshini toping.",
            correctAnswer = "ishyoqmas"
        ),
        Question.InputQuestion(
            id = 15,
            text = "“Harakatchan” so‘zining ma’nodoshini toping.",
            correctAnswer = "tirishqoq"
        ),
        Question.InputQuestion(
            id = 16,
            text = "“Sohibjamol” so‘zining ma’nodoshini toping.",
            correctAnswer = "hushro‘y"
        ),
        Question.InputQuestion(
            id = 17,
            text = "“Katta” so‘zining ma’nodoshini toping.",
            correctAnswer = "ulkan"
        ),
        Question.InputQuestion(
            id = 18,
            text = "“Qo’rqmas” so‘zining ma’nodoshini toping.",
            correctAnswer = "jasur"
        ),
        Question.InputQuestion(
            id = 19,
            text = "“Keng” so‘zining ma’nodoshini toping.",
            correctAnswer = "bepoyon"
        ),
        Question.InputQuestion(
            id = 20,
            text = "“Asir” so‘zining ma’nodoshini toping.",
            correctAnswer = "tutqun"
        ),

        // 3-qism: Zid ma’noli so‘zlar
        Question.InputQuestion(
            id = 21,
            text = "“Muloyim” so‘zining zid ma’nosini toping.",
            correctAnswer = "qo‘pol"
        ),
        Question.InputQuestion(
            id = 22,
            text = "“Lapashang” so‘zining zid ma’nosini toping.",
            correctAnswer = "uddaburon"
        ),
        Question.InputQuestion(
            id = 23,
            text = "“Yorug’” so‘zining zid ma’nosini toping.",
            correctAnswer = "qorong‘u"
        ),
        Question.InputQuestion(
            id = 24,
            text = "“Keng” so‘zining zid ma’nosini toping.",
            correctAnswer = "tor"
        ),
        Question.InputQuestion(
            id = 25,
            text = "“Past” so‘zining zid ma’nosini toping.",
            correctAnswer = "baland"
        ),
        Question.InputQuestion(
            id = 26,
            text = "“Issiq” so‘zining zid ma’nosini toping.",
            correctAnswer = "sovuq"
        ),
        Question.InputQuestion(
            id = 27,
            text = "“Oq” so‘zining zid ma’nosini toping.",
            correctAnswer = "qora"
        ),
        Question.InputQuestion(
            id = 28,
            text = "“Katta” so‘zining zid ma’nosini toping.",
            correctAnswer = "kichik"
        ),
        Question.InputQuestion(
            id = 29,
            text = "“Kamhosil” so‘zining zid ma’nosini toping.",
            correctAnswer = "serhosil"
        ),
        Question.InputQuestion(
            id = 30,
            text = "“Mehnatkash” so‘zining zid ma’nosini toping.",
            correctAnswer = "ishyoqmas"
        ),

        // 4-qism: “Ortiqcha so‘zni top”
        Question.ChoiceQuestion(
            id = 31,
            text = "Ortiqcha so‘zni toping.",
            options = listOf("chiroyli", "go‘zal", "hushro‘y", "bahaybat"),
            correctAnswer = "bahaybat"
        ),
        Question.ChoiceQuestion(
            id = 32,
            text = "Ortiqcha so‘zni toping.",
            options = listOf("botir", "jasur", "tirishqoq", "qo‘rqmas"),
            correctAnswer = "tirishqoq"
        ),
        Question.ChoiceQuestion(
            id = 33,
            text = "Ortiqcha so‘zni toping.",
            options = listOf("dangasa", "ishyoqmas", "harakatshang", "ezma"),
            correctAnswer = "ezma"
        ),
        Question.ChoiceQuestion(
            id = 34,
            text = "Ortiqcha so‘zni toping.",
            options = listOf("muloyim", "silliq", "dag‘al", "tekis"),
            correctAnswer = "dag‘al"
        ),

        // 5-qism: Shakldosh so‘zlar
        Question.InputQuestion(
            id = 35,
            text = ",Tut' (meva) so'zining shakldosh juftini toping",
            correctAnswer = "tut (ushlamoq)"
        ),
        Question.InputQuestion(
            id = 36,
            text = ",Chop' ( yugurmoq) so'zining shakldosh juftini toping",
            correctAnswer = "chop (kesmoq)"
        ),
        Question.InputQuestion(
            id = 37,
            text = ",Chang' (cholg'u asbobi)  so'zining shakldosh juftini toping.",
            correctAnswer = "chang (havo)"
        ),
        Question.InputQuestion(
            id = 38,
            text = "Uch (uchmoq) so'zining shakldosh juftini toping",
            correctAnswer = "uch (son)"
        ),
        Question.InputQuestion(
            id = 39,
            text = ",Yutmoq' (yemoq) so'zining shakldosh juftini toping",
            correctAnswer = "yutmoq (g'olib bo'lmoq)"
        ),

        // 6-qism: Maqolni davom ettiring
        Question.InputQuestion(
            id = 40,
            text = "Maqolni davom ettiring: “Kamtarga kamol ...”",
            correctAnswer = "manmanga zavol"
        ),
        Question.InputQuestion(
            id = 41,
            text = "Maqolni davom ettiring: “Kattaga hurmatda bo‘l ...”",
            correctAnswer = "kichikka izzatda"
        ),
        Question.InputQuestion(
            id = 42,
            text = "Maqolni davom ettiring: “Yaxshiga yondosh ...”",
            correctAnswer = "yomondan qoch"
        ),
        Question.InputQuestion(
            id = 43,
            text = "Maqolni davom ettiring: “Hunar, hunardan risqing ...”",
            correctAnswer = "unar"
        ),
        Question.InputQuestion(
            id = 44,
            text = "Maqolni davom ettiring: “Daryo suvini bahor toshirar ...”",
            correctAnswer = "odam qadrini mehnat oshirar"
        ),

        // 7-qism: Gap turlarini aniqlash
        Question.InputQuestion(
            id = 45,
            text = "“Men maktabdan erta keldim” gapining turi qaysi?",
            correctAnswer = "darak gap"
        ),
        Question.InputQuestion(
            id = 46,
            text = "“Akmal uy vazifalaringni bajardingmi?” gapining turi qaysi?",
            correctAnswer = "so‘roq gap"
        ),
        Question.InputQuestion(
            id = 47,
            text = "“Noila matematikadan uy vazifani bajar.” gapining turi qaysi?",
            correctAnswer = "buyruq gap"
        ),

        // 8-qism: “Xatoni top” mashqi
        Question.ChoiceQuestion(
            id = 48,
            text = "Xato yozilgan so‘zni toping.",
            options = listOf("xunar", "husnixat", "handalak"),
            correctAnswer = "xunar"
        ),
        Question.ChoiceQuestion(
            id = 49,
            text = "Xato yozilgan so‘zni toping.",
            options = listOf("uzum", "kitop", "mashina"),
            correctAnswer = "kitop"
        ),
        Question.ChoiceQuestion(
            id = 50,
            text = "Xato yozilgan so‘zni toping.",
            options = listOf("noyabr", "hujum", "baxor"),
            correctAnswer = "baxor"
        ),
        Question.ChoiceQuestion(
            id = 51,
            text = "Xato yozilgan so‘zni toping.",
            options = listOf("oyla", "o‘quvchi", "uchuvchi"),
            correctAnswer = "oyla"
        ),
        Question.ChoiceQuestion(
            id = 52,
            text = "Xato yozilgan so‘zni toping.",
            options = listOf("telefon", "vay-fay", "computer"),
            correctAnswer = "computer"
        ),
        Question.ChoiceQuestion(
            id = 53,
            text = "Xato yozilgan so‘zni toping.",
            options = listOf("urik", "uzum", "uycha"),
            correctAnswer = "urik"
        ),
        Question.ChoiceQuestion(
            id = 54,
            text = "Xato yozilgan so‘zni toping.",
            options = listOf("farzan", "kamol", "mulohaza"),
            correctAnswer = "farzan"
        ),
        Question.ChoiceQuestion(
            id = 55,
            text = "Xato yozilgan so‘zni toping.",
            options = listOf("g‘isht", "go‘sh", "musht"),
            correctAnswer = "go‘sh"
        ),

        // TOPISHMOQlar
        Question.InputQuestion(
            id = 56,
            text = "Topishmoq: “Shum kampir shumaloq kampir, ko‘taray desam dumaloq kampir.” Javobini yozing.",
            correctAnswer = "tandir"
        ),
        Question.InputQuestion(
            id = 57,
            text = "Topishmoq: “Senda bor, menda bor, olamda yo‘q, dunyoda bor.” Javobini yozing.",
            correctAnswer = "n harfi"
        ),
        Question.InputQuestion(
            id = 58,
            text = "Topishmoq: “Chin qushim chinni qushim, chin tepaga qo‘ndi qushim, tumshug‘ini yerga egib, xalqqa salom berdi qushim.” Javobini yozing.",
            correctAnswer = "choynak va piyola"
        ),
        Question.InputQuestion(
            id = 59,
            text = "Topishmoq: “Ikki o‘rtoq bir tanda, kezar bog‘u chamanda.” Javobini yozing.",
            correctAnswer = "kapalak"
        ),
        Question.InputQuestion(
            id = 60,
            text = "Topishmoq: “Bir ajoyib ishxona, ichi doim qishxona.” Javobini yozing.",
            correctAnswer = "muzlatkich"
        ),

        // Tasviriy ifodalar
        Question.InputQuestion(
            id = 61,
            text = "“Po‘lat qush” tasviriy ifodasi nimani bildiradi?",
            correctAnswer = "samolyot"
        ),
        Question.InputQuestion(
            id = 62,
            text = "“Temir ot” tasviriy ifodasi nimani bildiradi?",
            correctAnswer = "traktor"
        ),
        Question.InputQuestion(
            id = 63,
            text = "“Dala malikasi” tasviriy ifodasi nimani bildiradi?",
            correctAnswer = "makka"
        ),
        Question.InputQuestion(
            id = 64,
            text = "“O‘rmon shohi” tasviriy ifodasi nimani bildiradi?",
            correctAnswer = "sher"
        ),
        Question.InputQuestion(
            id = 65,
            text = "“Fasllar kelinchagi” tasviriy ifodasi nimani bildiradi?",
            correctAnswer = "bahor"
        ),
        Question.InputQuestion(
            id = 66,
            text = "“Oltin to‘p” tasviriy ifodasi nimani bildiradi?",
            correctAnswer = "quyosh"
        ),
        Question.InputQuestion(
            id = 67,
            text = "“Ipak tola” tasviriy ifodasi nimani bildiradi?",
            correctAnswer = "pilla"
        ),
        Question.InputQuestion(
            id = 68,
            text = "“Zangori ekran” tasviriy ifodasi nimani bildiradi?",
            correctAnswer = "televizor"
        ),
        Question.InputQuestion(
            id = 69,
            text = "“Oq oltin” tasviriy ifodasi nimani bildiradi?",
            correctAnswer = "paxta"
        ),

        // 3-sinf ona tili testlari
        Question.ChoiceQuestion(
            id = 70,
            text = "Olma so‘zida nechta tovush bor?",
            options = listOf("3", "4", "5"),
            correctAnswer = "4"
        ),
        Question.ChoiceQuestion(
            id = 71,
            text = "Qaysi so‘zda juft undosh mavjud?",
            options = listOf("kitob", "qiz", "mis"),
            correctAnswer = "mis"
        ),
        Question.ChoiceQuestion(
            id = 72,
            text = "Qaysi bo‘g‘inlash to‘g‘ri?",
            options = listOf("ma-shi-na", "mash-ina", "mash-i-na"),
            correctAnswer = "ma-shi-na"
        ),
        Question.ChoiceQuestion(
            id = 73,
            text = "Qaysi so‘z jarangli undosh bilan tugaydi?",
            options = listOf("kitob", "qalam", "olma"),
            correctAnswer = "kitob"
        ),
        Question.ChoiceQuestion(
            id = 74,
            text = "“Yoz” so‘ziga qarama-qarshi ma’no qaysi?",
            options = listOf("issiq", "qish", "sovuq"),
            correctAnswer = "qish"
        ),
        Question.ChoiceQuestion(
            id = 75,
            text = "Otni belgilang.",
            options = listOf("tez", "stol", "yugurdi"),
            correctAnswer = "stol"
        ),
        Question.ChoiceQuestion(
            id = 76,
            text = "Qaysi so‘z sifat so‘z turkumiga oid?",
            options = listOf("katta", "uy", "o‘qimoqda"),
            correctAnswer = "katta"
        ),
        Question.ChoiceQuestion(
            id = 77,
            text = "“Men” so‘zi qaysi so‘z turkumiga kiradi?",
            options = listOf("olmosh", "son", "ravish"),
            correctAnswer = "olmosh"
        ),
        Question.ChoiceQuestion(
            id = 78,
            text = "Fe’l so‘z turkumiga oid so‘zni toping.",
            options = listOf("o‘quvchi", "o‘qimoqda", "kitob"),
            correctAnswer = "o‘qimoqda"
        ),
        Question.ChoiceQuestion(
            id = 79,
            text = "“Uchta” qaysi so‘z turkumi?",
            options = listOf("son", "ot", "sifat"),
            correctAnswer = "son"
        ),
        Question.ChoiceQuestion(
            id = 80,
            text = "Gap bosh harf bilan boshlanib, nima bilan tugaydi?",
            options = listOf("vergul", "tinish belgisi", "qo‘shimcha"),
            correctAnswer = "tinish belgisi"
        ),
        Question.ChoiceQuestion(
            id = 81,
            text = "Ega qaysi so‘roqlarga javob bo‘ladi?",
            options = listOf("kim? nima?", "qayerda?", "qachon?"),
            correctAnswer = "kim? nima?"
        ),
        Question.ChoiceQuestion(
            id = 82,
            text = "“Gul ochildi.” gapida kesim qaysi so‘z?",
            options = listOf("gul", "ochildi"),
            correctAnswer = "ochildi"
        ),
        Question.ChoiceQuestion(
            id = 83,
            text = "Qaysi gap so‘roq gap?",
            options = listOf(
                "Maktab hovlisi keng.",
                "Sen bugun dars qildingmi?",
                "Qanday chiroyli!"
            ),
            correctAnswer = "Sen bugun dars qildingmi?"
        ),
        Question.ChoiceQuestion(
            id = 84,
            text = "“Bolalar o‘ynayapti” gapiga mos tinish belgisi qaysi?",
            options = listOf(".", "!", "?"),
            correctAnswer = "."
        ),
        Question.ChoiceQuestion(
            id = 85,
            text = "To‘g‘ri yozilgan so‘zni belgilang.",
            options = listOf("qizqin", "issiq", "ossin"),
            correctAnswer = "issiq"
        ),
        Question.ChoiceQuestion(
            id = 86,
            text = "To‘g‘ri yozilgan juftlikni toping.",
            options = listOf("qalin – yupqa", "qalin – yuqpa", "qalyn – yupqa"),
            correctAnswer = "qalin – yupqa"
        ),
        Question.ChoiceQuestion(
            id = 87,
            text = "“Bog‘bon” so‘zi nechta bo‘g‘indan iborat?",
            options = listOf("1", "2", "3"),
            correctAnswer = "2"
        ),
        Question.ChoiceQuestion(
            id = 88,
            text = "“O‘qituvchi” so‘zida nechta undosh tovush bor?",
            options = listOf("3", "4", "5"),
            correctAnswer = "5"
        ),
        Question.InputQuestion(
            id = 89,
            text = "Rasmni nomlang.",
            correctAnswer = "Zaharli tutun",
            imageResId = R.drawable.tutun
        ),
        Question.InputQuestion(
            id = 90,
            text = "Rasmni nomlang.",
            correctAnswer = "yoz fasli",
            imageResId = R.drawable.yoz
        ),
        Question.InputQuestion(
            id = 91,
            text = "Rasmda daraxt barglari to‘kilib tushayotgani tasvirlangan. Bu qanday holat?",
            correctAnswer = "hazonrezgi",
            imageResId = R.drawable.hazonrezgi
        ),
        Question.InputQuestion(
            id = 92,
            text = "Rasmni nomlang.",
            correctAnswer = "savodxonlik bayrami",
            imageResId = R.drawable.maktab
        ),
        Question.InputQuestion(
            id = 93,
            text = "Rasmni nomlang.",
            correctAnswer = "sayoxat",
            imageResId = R.drawable.sayoxat
        ),
        Question.InputQuestion(
            id = 94,
            text = "Rasmni nomlang.",
            correctAnswer = "sog'лом turmush tarzi",
            imageResId = R.drawable.ovqat
        ),
        Question.InputQuestion(
            id = 95,
            text = "Rasmni nomlang.",
            correctAnswer = "baxtli oila",
            imageResId = R.drawable.oila
        )

        // Агар хоҳласанг, 4-синф тестлар блокини ҳам шу тарзда давом эттирамиз
    )
}

// Состояние прохождения тестов (результаты)
data class QuestionResult(
    val questionId: Int,
    val questionText: String,
    val isCorrect: Boolean
)

object QuizState {
    val results = mutableListOf<QuestionResult>()

    fun clear() {
        results.clear()
    }

    fun addResult(question: Question, isCorrect: Boolean) {
        results.add(
            QuestionResult(
                questionId = question.id,
                questionText = question.text,
                isCorrect = isCorrect
            )
        )
    }
}
