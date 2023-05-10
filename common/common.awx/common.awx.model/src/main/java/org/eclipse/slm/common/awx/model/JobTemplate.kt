package org.eclipse.slm.common.awx.model

import java.util.*

data class JobTemplate(
        var id: Int,
        var type: String,
        var url: String,
        var related: Map<String, String>,
        var summary_fields: Map<String, Any>,
        var created: Date,
        var modified: Date,
        var name: String,
        var description: String,
        var job_type: String,
        var inventory: Int,
        var project: Int,
        var playbook: String,
        var scm_branch: String,
        var forks: Int,
        var limit: String,
        var verbosity: Int,
        var extra_vars: String,
        var job_tags: String,
        var force_handlers: Boolean,
        var skip_tags: String,
        var start_at_task: String,
        var timeout: Int,
        var use_fact_cache: Boolean,
        var organization: Int,
        var last_job_run: Date?,
        var last_job_failed: Boolean,
        var next_job_run: String?,
        var status: String,
        var host_config_key: String,
        var ask_scm_branch_on_launch: Boolean,
        var ask_diff_mode_on_launch: Boolean,
        var ask_variables_on_launch: Boolean,
        var ask_limit_on_launch: Boolean,
        var ask_tags_on_launch: Boolean,
        var ask_skip_tags_on_launch: Boolean,
        var ask_job_type_on_launch: Boolean,
        var ask_verbosity_on_launch: Boolean,
        var ask_inventory_on_launch: Boolean,
        var ask_credential_on_launch: Boolean,
        var survey_enabled: Boolean,
        var become_enabled: Boolean,
        var diff_mode: Boolean,
        var allow_simultaneous: Boolean,
        var custom_virtualenv: String?,
        var job_slice_count: Int,
        var webhook_service: String,
        var webhook_credential: String?
)
