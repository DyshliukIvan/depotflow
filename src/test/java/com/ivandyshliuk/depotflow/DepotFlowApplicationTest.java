package com.ivandyshliuk.depotflow;

import com.ivandyshliuk.depotflow.inventory.StockItem;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DepotFlowApplicationTest {

    @Test
    void applicationClassExists() {
        assertThat(DepotFlowApplication.class).isNotNull();
    }
}
