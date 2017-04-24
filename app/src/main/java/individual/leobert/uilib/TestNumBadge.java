package individual.leobert.uilib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import individual.leobert.uilib.numbadge.NumBadge;

public class TestNumBadge extends AppCompatActivity implements View.OnClickListener{
    private NumBadge badgeOval, badgeRectAngle, badgeDefault;
    private NumBadge[] numBadges;

    private Button btnTestDot;
    private Button btnTestNum1;
    private Button btnTestNum8;
    private Button btnTestNum88;
    private Button btnTestNum888;

    /**
     * the max num to display,if lager than it,will display as [MAX_BADGE_NUM]+
     * e.g. use MAX_BADGE_NUM = 99,when you set 123 via #updateWithFriendlyMode(num,MAX_BADGE_NUM);
     * will display as 99+
     */
    private static final int MAX_BADGE_NUM = 99;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_num_badge);

        badgeOval = (NumBadge) findViewById(R.id.numbadge_1);
        badgeRectAngle = (NumBadge) findViewById(R.id.numbadge_2);
        badgeDefault = (NumBadge) findViewById(R.id.numbadge_3);

        btnTestDot = (Button) findViewById(R.id.numbadge_test_1);
        btnTestNum1 = (Button) findViewById(R.id.numbadge_test_2);
        btnTestNum8 = (Button) findViewById(R.id.numbadge_test_3);
        btnTestNum88 = (Button) findViewById(R.id.numbadge_test_4);
        btnTestNum888 = (Button) findViewById(R.id.numbadge_test_5);

        btnTestDot.setOnClickListener(this);
        btnTestNum1.setOnClickListener(this);
        btnTestNum8.setOnClickListener(this);
        btnTestNum88.setOnClickListener(this);
        btnTestNum888.setOnClickListener(this);


        numBadges = new NumBadge[]{
                badgeOval, badgeRectAngle, badgeDefault
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.numbadge_test_1:
                testDot();
                break;
            case R.id.numbadge_test_2:
                testSetNumActually(1);
                break;
            case R.id.numbadge_test_3:
                testSetNumActually(8);
//                testSetNumFriendly(8);
                break;
            case R.id.numbadge_test_4:
                testSetNumActually(88);
//                testSetNumFriendly(88);
                break;
            case R.id.numbadge_test_5:
//                testSetNumActually(888);
                testSetNumFriendly(888);
                break;
            default:
                break;
        }

    }

    private void testDot() {
        for (NumBadge numBadge:numBadges) {
            numBadge.updateWithPointMode();
        }
    }

    private void testSetNumFriendly(int num) {
        for (NumBadge numBadge:numBadges) {
            numBadge.updateWithFriendlyMode(num,MAX_BADGE_NUM);
        }
    }

    private void testSetNumActually(int num) {
        for (NumBadge numBadge:numBadges) {
            numBadge.updateWithActualMode(num);
        }
    }
}
