package com.pair.CreateProblem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProblemCheckTest {

    @Test
    void check() {
        ProblemCheck.CheckPro(new String[]{
                "3'8/9 + 9'1/3 - 5'4/7 = ",
                "1/6 × 1'1/2 ÷ 6'1/2 = ",
                "2'1/2 + 3'5/9 ÷ 2'3/4 = ",
                "3'2/5 × 7'2/3 - 2'3/4 = ",
                "6'6/7 ÷ 9'1/2 = ",
                "7'1/3 + 9'4/5 + 2/5 =",
                "5'4/5 - 6'1/4 + 3'6/7 = ",
                "3'1/2 ÷ 6'1/9 + 3'1/2 × 4 = ",
                "6'1/2 + 1/2 + 7'3/7 = ",
                "4'1/2 + 9'4/7 ÷ 7'1/2 = "});
    }
}