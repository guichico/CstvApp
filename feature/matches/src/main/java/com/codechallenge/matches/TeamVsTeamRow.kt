package com.codechallenge.matches

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codechallenge.designsystem.components.CstvAsyncImage
import com.codechallenge.designsystem.theme.CstvAppTheme
import com.codechallenge.designsystem.theme.VSColor
import com.codechallenge.model.Team

@Composable
private fun TeamColumn(
    team: Team
) {
    Column {
        CstvAsyncImage(
            modifier = Modifier
                .size(60.dp),
            url = team.logoUrl
        )
        Text(
            modifier = Modifier
                .padding(top = CstvAppTheme.spacing.medium)
                .align(Alignment.CenterHorizontally),
            text = team.name,
            style = CstvAppTheme.typography.robotoRegular2
        )
    }
}

@Composable
internal fun ColumnScope.TeamVsTeamRow(
    modifier: Modifier = Modifier,
    homeTeam: Team,
    visitingTeam: Team
) {
    Row(
        modifier = modifier
            .wrapContentSize()
            .align(Alignment.CenterHorizontally)
    ) {
        TeamColumn(team = homeTeam)
        Text(
            modifier = Modifier
                .padding(
                    horizontal = CstvAppTheme.spacing.extraLarge
                )
                .align(Alignment.CenterVertically),
            text = "vs",
            style = CstvAppTheme.typography.robotoRegular3,
            color = VSColor
        )
        TeamColumn(team = visitingTeam)
    }
}

@Preview
@Composable
private fun TeamVsTeamRowPreview(
) {
    /*
    CstvAppTheme {
        Column {
            TeamVsTeamRow(
                homeTeam = homeTeam,
                visitingTeam = visitingTeam
            )
        }
    }
     */
}
