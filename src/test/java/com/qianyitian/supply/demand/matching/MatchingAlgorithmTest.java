package com.qianyitian.supply.demand.matching;

import com.amdelamar.jotp.OTP;
import com.amdelamar.jotp.type.Type;
import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.KeyGenerator;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatchingAlgorithmTest {
    MatchingAlgorithm matchingAlgorithm = null;

    @BeforeEach
    void setUp() {
        matchingAlgorithm = new MatchingAlgorithm();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void run() throws IOException {
        //加载数据
        SupplyInput supplyInput = DataLoader.loadSupply("supply-1.data");
        DemandInput demandInput = DataLoader.loadDemand("demand-1.data");

        //实际运行结果
        MatchingOutput runningResult = matchingAlgorithm.run(supplyInput, demandInput);

        //期望结果
        MatchingOutput expectedResult = DataLoader.loadResult("output-1.data");

        //实际结果和期望结果比较
        compare(runningResult.getResult(), expectedResult.getResult());

    }

    private void compare(List<MatchingResult> result1, List<MatchingResult> result2) {
        assertEquals(result1.size(), result2.size());
        for (int i = 0; i < result1.size(); i++) {
            assertEquals(result1.get(i), result2.get(i));
        }
    }

    @Test
    void run1() throws IOException, InvalidKeyException, NoSuchAlgorithmException {
        final TimeBasedOneTimePasswordGenerator totp = new TimeBasedOneTimePasswordGenerator(Duration.ofSeconds(30));
        final KeyGenerator keyGenerator = KeyGenerator.getInstance(totp.getAlgorithm());
        keyGenerator.init(160);

        Key key = keyGenerator.generateKey();

        final Instant now = Instant.now();
        final Instant later = now.plusSeconds(5);
        ;

        System.out.println("Current password: " + totp.generateOneTimePasswordString(key, now));
//        System.out.println("Future password:  " + totp.generateOneTimePasswordString(key, later));
    }

    @Test
    public void run2() throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        // Random secret Base32 with 20 bytes (160 bits) length
// (Use this to setup 2FA for new accounts).
        String secret = "IM4ZL3G5Q66KW4U7PMOQVXQQH3NGOCHQ";
// Returns: IM4ZL3G5Q66KW4U7PMOQVXQQH3NGOCHQ

// Generate a Time-based OTP from the secret, using Unix-time
// rounded down to the nearest 30 seconds.
        String hexTime = OTP.timeInHex(System.currentTimeMillis(), 300);
        String code = OTP.create(secret, hexTime, 6, Type.TOTP);
        System.out.println(code);

        {
            String time = OTP.timeInHex(System.currentTimeMillis(), 300);
            String code2 = OTP.create(secret, hexTime, 6, Type.TOTP);
            System.out.println(code2);
        }

        String hexTime2 = OTP.timeInHex(System.currentTimeMillis(), 300);
        boolean ok = OTP.verify(secret, hexTime2, code, 6, Type.TOTP);
        System.out.println(ok);
    }

    @Test
    public void run3() throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        // Get User's input code for a login...
        String userEnteredCode = "877200";
        String secret = "IM4ZL3G5Q66KW4U7PMOQVXQQH3NGOCHQ";
// Verify OTP
        String hexTime = OTP.timeInHex(System.currentTimeMillis(), 300);
        boolean ok = OTP.verify(secret, hexTime, userEnteredCode, 6, Type.TOTP);
        System.out.println(ok);


    }

}