package com.example.b10709012_hw2.data;

import android.provider.BaseColumns;

public class WaitListContract {
    public static final class WaitListEntry implements BaseColumns {

        public static final String TABLE_NAME = "waitlist";
        public static final String COLUMN_GUEST_NAME = "guestName";
        public static final String COLUMN_PARTY_SIZE = "partySize";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

}
