package com.example.word_io.data

import com.example.word_io.model.Question

class DataSource {
    fun loadQuestions(): List<Question> {
        return listOf<Question>(
            Question(
                "basket",
                "a container used to hold or carry things, typically made from interwoven strips of cane or wire"
            ),
            Question(
                "book",
                "a written or printed work consisting of pages glued or sewn together along one side and bound in"
            ),
            Question(
                "bell",
                "a hollow metal object shaped like a cup that makes a ringing sound when hit by something hard, especially a clapper"
            ),
            Question(
                "boat",
                "a small vehicle for travelling on water"
            ),
            Question(
                "cake",
                "an item of soft, sweet food made from a mixture of flour, shortening, eggs, sugar, and other ingredients, baked and often decorated"
            ),
            Question(
                "gift",
                "a thing given willingly to someone without payment"
            ),
            Question(
                "spoon",
                "an implement consisting of a small, shallow oval or round bowl on a long handle, used for eating, stirring, and serving food"
            ),
            Question(
                "house",
                "a building for human habitation, especially one that is lived in by a family or small group of people"
            ),
            Question(
                "juice",
                "the liquid obtained from or present in fruit or vegetables"
            ),
            Question(
                "ball",
                "a solid or hollow spherical or egg-shaped object that is kicked, thrown, or hit in a game"
            ),
        )
    }
}